package com.gumigames.data.interceptor

import android.os.Build.VERSION_CODES.N
import android.provider.Telephony.Carriers.BEARER
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.gumigames.data.BuildConfig
import com.gumigames.data.BuildConfig.BASE_URL
import com.gumigames.data.datasource.sharedpreference.PreferenceDataSource
import com.gumigames.data.model.response.basic.AuthResponse
import com.gumigames.data.model.response.basic.ErrorResponse
import com.gumigames.data.service.UserService
import com.gumigames.domain.model.user.LoginDto
import com.gumigames.domain.util.NetworkThrowable
import com.gumigames.domain.util.getValueOrThrow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.internal.closeQuietly
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TAG = "차선호"
class AuthInterceptor(
    private val preferenceDataSource: PreferenceDataSource,
): Interceptor {
    private val gson = Gson()
    private val client = OkHttpClient.Builder().build()

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d(TAG, "현재 accessToken : ${getAccessToken()}")
        val accessToken = getAccessToken() ?: return chain.proceed(chain.request()) //accessToken이 null이면 바로 통신(최초 로그인 경우)

        val headerAddedRequest = chain.request().newBuilder().addHeader(AUTHORIZATION, BEARER + accessToken).build() //헤더에 ACCESS 토큰 저장
        val response: Response = chain.proceed(headerAddedRequest) //Response 받기

        if (response.code == AUTH_TOKEN_ERROR) { //ACCESS_TOKEN이 만료라면
            val errorResponse = parseErrorResponse(response.body)
            when(errorResponse.errorCode){
                //ACCESS TOKEN 만료
                ACCESS_TOKEN_EXPIRED -> {
                    Log.d(TAG, "access token 만료")
//                    val result = Retrofit.Builder()
//                        .baseUrl(BASE_URL)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build()
//                        .create(UserService::class.java).get("Bearer ${it}")

                    val newAccessToken = getAccessTokenWithRefresh(accessToken).getOrElse {
                        Log.d(TAG, "getAccessTokenWithREfersh에서 else로 빠짐")
                        throw IOException(it)
                    }
//                    val newAccessToken = getAccessTokenWithRefresh(accessToken).getOrElse { return response }
                    response.closeQuietly()
                    return chain.proceed(chain.request().newBuilder().addHeader(AUTHORIZATION, BEARER + newAccessToken).build()) //재발급 받은 ACCESS TOKEN 헤더에 넣고 최초에 시도했던 통신 재개
                }
                NO_APPLY_TOKEN ->{}
                NO_SIGNATURE_TOKEN -> {}
                INVALID_TOKEN -> {}
                NOT_SAME_REFRESH_TOKEN -> {}
                //REFRESH TOKEN 없음
                NOT_FOUND_REFRESH_TOKEN -> {
                    Log.d(TAG, "intercept -> not found refresh token")}
                //REFRESH TOKEN 만료
                REFRESH_TOKEN_EXPIRED ->{
                    //TODO PREFERENCE 초기화 해줘라
                    Log.d(TAG, "intercept: 이거 뜨면 된 거 ")
                    throw NetworkThrowable.RefreshExpireThrowable()
                }
                //ACCESS_TOKEN 없음
                NOT_FOUND_ACCESS_TOKEN ->{}
            }
        }
        return response
    }

    //Refresh 토큰으로 AccessToken 재발급
    private fun getAccessTokenWithRefresh(accessToken: String): Result<String> {
        val request = createRefreshRequest()

//        val auth: AuthResponse = requestRefresh(request).get

        val auth: AuthResponse = requestRefresh(request).getOrElse {
            Log.d(TAG, "getAccessTokenWithRefresh 실패 : $it")
            return Result.failure(it)
        }
        storeToken(auth.accessToken, auth.refreshToken)
        return Result.success(BEARER + auth.accessToken)
    }

    private fun createRefreshRequest(): Request {
        Log.d(TAG, "createRefreshRequest에서 현재 refreshToken : ${getRefreshToken()}")
        return Request.Builder()
            .url(BuildConfig.BASE_URL + AUTH_REFRESH_PATH) //ACCESS TOKEN 재발급 통신
            .post(ByteArray(0).toRequestBody(null, 0))
            .addHeader(AUTH_REFRESH_KEY, BEARER + getRefreshToken()) //여기에 이제 REFRESH TOKEN을 헤더에 넣어라
            .build()
    }

    private fun requestRefresh(request: Request): Result<AuthResponse> {
        Log.d(TAG, "requestRefresh에서 request : $request")


        val response: Response = runBlocking {
            withContext(Dispatchers.IO) { client.newCall(request).execute() } //재발급 하는 통신 실행
        }
        if (response.isSuccessful) { //재발급 성공
            Log.d(TAG, "access-token 재발급 성공")
            return Result.success(response.getDto<AuthResponse>()) // 새로운 AuthResponse 반환
        }
        val errorCode = parseErrorResponse(response.body).errorCode
        when(errorCode){
            REFRESH_TOKEN_EXPIRED -> return Result.failure(NetworkThrowable.RefreshExpireThrowable())
        }
        return Result.failure(IllegalStateException(REFRESH_FAILURE))
    }

    private fun getAccessToken(): String? {
        return preferenceDataSource.getAccessToken()
    }

    private fun getRefreshToken(): String {
        return requireNotNull(preferenceDataSource.getRefreshToken()) { NO_REFRESH_TOKEN }
    }

    private fun storeToken(accessToken: String, refreshToken: String) {
        preferenceDataSource.setAccessToken(accessToken)
        preferenceDataSource.setRefreshToken(refreshToken)
    }

    private inline fun <reified T> Response.getDto(): T {
        val responseObject = JsonParser.parseString(body?.string()).asJsonObject
        return gson.fromJson(responseObject, T::class.java)
    }

    private fun parseErrorResponse(responseBody: ResponseBody?): ErrorResponse {
        val gson = Gson()
        return gson.fromJson(responseBody?.charStream(), ErrorResponse::class.java)
    }

    companion object {
        private const val AUTHORIZATION = "Authorization" //ACCESS_TOEKN KEY 이름
        private const val AUTH_REFRESH_KEY = "Authorization-Refresh" //REFRESH_TOKEN KEY 이름

        private const val AUTH_REFRESH_PATH = "api/re-issue" //accessToken 재발급 하는 api 주소

        private const val BEARER = "Bearer "

        private const val NO_REFRESH_TOKEN = "리프레시 토큰이 없습니다"
        private const val REFRESH_FAILURE = "토큰 리프레시 실패"

        private const val ACCESS_TOKEN_EXPIRED = "Auth001"
        private const val NO_APPLY_TOKEN = "Auth002"
        private const val NO_SIGNATURE_TOKEN = "Auth003"
        private const val INVALID_TOKEN = "Auth004"
        private const val NOT_SAME_REFRESH_TOKEN = "Auth005"
        private const val NOT_FOUND_REFRESH_TOKEN = "Auth006"
        private const val REFRESH_TOKEN_EXPIRED = "Auth007"
        private const val NOT_FOUND_ACCESS_TOKEN = "Auth008"
        private const val AUTH_TOKEN_ERROR = 401 // 토큰 에러 코드
    }
}


/**
 * 001 엑세스 토큰 만료
 * 002 지원하지 않는 토큰
 * 003 유효하지 않은 서명
 * 004 유효하지 않는 토큰(엑세스 리프레시 둘 다)
 * 005 리프레시 토큰이 해당 아이디의 토큰과 불일치
 * 006 리프레시 토큰 미입력
 * 007 리프레시 토큰 만료
 * 008 엑세스 토큰 없음
 */
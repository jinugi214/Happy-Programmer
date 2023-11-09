package com.gumigames.data.interceptor

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.gumigames.data.BuildConfig
import com.gumigames.data.datasource.sharedpreference.PreferenceDataSource
import com.gumigames.data.model.response.basic.AuthResponse
import com.gumigames.data.model.response.basic.ErrorResponse
import com.gumigames.domain.util.NetworkThrowable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.internal.closeQuietly

private const val TAG = "차선호"
class AuthInterceptor(
    private val preferenceDataSource: PreferenceDataSource
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
                ACCESS_TOKEN_EXPRIED -> {
                    Log.d(TAG, "access token 만료")
                    val newAccessToken = getAccessTokenWithRefresh(accessToken).getOrElse { return response }
                    response.closeQuietly()
                    return chain.proceed(chain.request().newBuilder().addHeader(AUTHORIZATION, BEARER + newAccessToken).build()) //재발급 받은 ACCESS TOKEN 헤더에 넣고 최초에 시도했던 통신 재개
                }
            }
        }
        return response
    }

    //Refresh 토큰으로 AccessToken 재발급
    private fun getAccessTokenWithRefresh(accessToken: String): Result<String> {
        val request = createRefreshRequest()

        val auth: AuthResponse = requestRefresh(request).getOrElse {
            return Result.failure(it)
        }
        storeToken(auth.accessToken, auth.refreshToken)
        return Result.success(BEARER + auth.accessToken)
    }

    private fun createRefreshRequest(): Request {
        Log.d(TAG, "createRefreshRequest에서 현재 refreshToken : ${getRefreshToken()}")
        return Request.Builder()
            .url(BuildConfig.BASE_URL + AUTH_REFRESH_PATH) //ACCESS TOKEN 재발급 통신
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
        Log.d(TAG, "access 재발급 실패 : $response")
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

        private const val ACCESS_TOKEN_EXPRIED = "Auth001"
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
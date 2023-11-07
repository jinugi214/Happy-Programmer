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

        Log.d(TAG, "현재 response : $response")
        if (response.code == AUTH_TOKEN_EXPIRE_ERROR) { //ACCESS_TOKEN이 만료라면
            val newAccessToken = getAccessTokenWithRefresh(accessToken).getOrElse { return response }
            response.closeQuietly()
            return chain.proceed(chain.request().newBuilder().addHeader(AUTHORIZATION, BEARER + newAccessToken).build()) //재발급 받은 ACCESS TOKEN 헤더에 넣고 최초에 시도했던 통신 재개
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
        return Request.Builder()
            .url(BuildConfig.BASE_URL + AUTH_REFRESH_PATH) //ACCESS TOKEN 재발급 통신
            .addHeader(AUTH_REFRESH_KEY, BEARER + getRefreshToken()) //여기에 이제 REFRESH TOKEN을 헤더에 넣어라
            .build()
    }

    private fun requestRefresh(request: Request): Result<AuthResponse> {
        val response: Response = runBlocking {
            withContext(Dispatchers.IO) { client.newCall(request).execute() } //재발급 하는 통신 실행
        }
        if (response.isSuccessful) { //재발급 성공
            return Result.success(response.getDto<AuthResponse>()) // 새로운 AuthResponse 반환
        }
        val failedResponse = response.getDto<ErrorResponse>()
        if (failedResponse.code == REFRESH_TOKEN_EXPIRE_ERROR) { //REFRESH TOKEN 만료로 accessToken 재발급 하는 것이 실패
            //preference 초기화하고 RefrestToken 만료 Throwable throw
            preferenceDataSource.refreshPreference()
            return Result.failure(NetworkThrowable.RefreshExpireThrowable())
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

    companion object {
        private const val AUTHORIZATION = "Authorization" //ACCESS_TOEKN KEY 이름
        private const val AUTH_REFRESH_KEY = "Authorization-Refresh" //REFRESH_TOKEN KEY 이름

        private const val AUTH_REFRESH_PATH = "/api/account/new/access-token" //서버에서 주는 accessToken 재발급 하는 api 주소

        private const val BEARER = "Bearer "

        private const val NO_REFRESH_TOKEN = "리프레시 토큰이 없습니다"
        private const val REFRESH_FAILURE = "토큰 리프레시 실패"

        private const val AUTH_TOKEN_EXPIRE_ERROR = 401 // TODO 서버에 맞게 수정
        private const val REFRESH_TOKEN_EXPIRE_ERROR = 101 // TODO 서버에 맞게 수정
    }
}
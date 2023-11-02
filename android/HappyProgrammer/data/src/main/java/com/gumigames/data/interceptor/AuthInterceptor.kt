package com.gumigames.data.interceptor

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.gumigames.data.BuildConfig
import com.gumigames.data.datasource.local.PreferenceDataSource
import com.gumigames.data.entity.response.AuthDto
import com.gumigames.data.entity.response.ErrorDto
import com.gumigames.domain.util.NetworkThrowable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.internal.closeQuietly
import org.json.JSONObject

class AuthInterceptor(
    private val preferenceDataSource: PreferenceDataSource
): Interceptor {
    private val gson = Gson()
    private val client = OkHttpClient.Builder().build()

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = getAccessToken() ?: return chain.proceed(chain.request()) //accessToken이 null이면 바로 통신

        val headerAddedRequest = chain.request().newBuilder().addHeader(AUTHORIZATION, BEARER + accessToken).build() //헤더에 토큰 저장
        val response: Response = chain.proceed(headerAddedRequest) //Response 받기

        if (response.code == AUTH_TOKEN_EXPIRE_ERROR) {
            val newAccessToken = getAccessTokenWithRefresh(accessToken).getOrElse { return response }
            response.closeQuietly()
            return chain.proceed(chain.request().newBuilder().addHeader(AUTHORIZATION, BEARER + newAccessToken).build())
        }
        return response
    }

    //Refresh 토큰으로 AccessToken 재발급
    private fun getAccessTokenWithRefresh(accessToken: String): Result<String> {
        val request = createRefreshRequest()

        val auth: AuthDto = requestRefresh(request).getOrElse {
            return Result.failure(it)
        }
        storeToken(auth.accessToken, auth.refreshToken)
        return Result.success(BEARER + auth.accessToken)
    }

    private fun createRefreshRequest(): Request {
        return Request.Builder()
            .url(BuildConfig.BASE_URL + AUTH_REFRESH_PATH)
            .addHeader(AUTHORIZATION, BEARER + getRefreshToken())
            .build()
    }

    private fun requestRefresh(request: Request): Result<AuthDto> {
        val response: Response = runBlocking {
            withContext(Dispatchers.IO) { client.newCall(request).execute() }
        }
        if (response.isSuccessful) {
            return Result.success(response.getDto<AuthDto>())
        }
        val failedResponse = response.getDto<ErrorDto>()
        if (failedResponse.code == REFRESH_TOKEN_EXPIRE_ERROR) { //accessToken 재발급 하는 것이 실패
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
        private const val AUTHORIZATION = "Authorization"
        private const val AUTH_REFRESH_KEY = "refreshToken"

        private const val AUTH_REFRESH_PATH = "/auth/refresh" //서버에서 주는 accessToken 재발급 하는 api 주소

        private const val BEARER = "Bearer "

        private const val NO_REFRESH_TOKEN = "리프레시 토큰이 없습니다"
        private const val REFRESH_FAILURE = "토큰 리프레시 실패"

        private const val AUTH_TOKEN_EXPIRE_ERROR = 401 // TODO 서버에 맞게 수정
        private const val REFRESH_TOKEN_EXPIRE_ERROR = 101 // TODO 서버에 맞게 수정
    }
}
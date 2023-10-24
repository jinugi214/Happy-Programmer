package com.freeproject.happyprogrammers.data.datasource

interface PreferenceDataSource {
    fun getAccessToken(): String?
    fun setAccessToken(newToken: String)
    fun getRefreshToken(): String?
    fun setRefreshToken(newToken: String)
    fun resetToken()
    fun getIsLogined(): Boolean
    fun setIsLogined(value: Boolean)
}
package com.gumigames.data.datasource.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class PreferenceDataSourceImpl(
    context: Context
): PreferenceDataSource {
    companion object{
        private const val AUTH_ENCRYPTED_PREFERENCE = "happy_programmer"
        private const val X_ACCESS_TOKEN = "access_token"
        private const val X_REFRESH_TOKEN = "refresh_token"
        private const val X_ROLE = "role"
        private const val FCM_TOKEN = "fcm_token"
        private const val ROOM_ID = "room_id"
        private const val IS_SHOWED_PERMISSION_DIALOG = "is_showed_permission_dialog"
        private const val IS_LOGINED = "is_logined"
    }
    private fun getPreference(context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        return EncryptedSharedPreferences.create(
            context,
            AUTH_ENCRYPTED_PREFERENCE,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    }
    private val prefs by lazy { getPreference(context) }
    private val editor by lazy { prefs.edit() }

    private fun putString(key: String, data: String?){
        editor.putString(key, data)
        editor.apply()
    }
    private fun getString(key: String, defValue: String? = null): String? {
        return prefs.getString(key, defValue)
    }

    private fun putBoolean(key: String, value: Boolean){
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun getBoolean(key: String): Boolean {
        return prefs.getBoolean(key, false)
    }

    /**
     * Preference 초기화 (로그아웃 시에 호출해주자)
     */
    override fun refreshPreference() {
        putString(X_ACCESS_TOKEN, null)
        putString(X_REFRESH_TOKEN, null)
        putString(IS_LOGINED, null)
        putString(IS_SHOWED_PERMISSION_DIALOG, null)
    }

    override fun getAccessToken(): String? {
        return getString(X_ACCESS_TOKEN)
    }

    override fun setAccessToken(newToken: String) {
        putString(X_ACCESS_TOKEN, newToken)
    }

    override fun getRefreshToken(): String? {
        return getString(X_REFRESH_TOKEN)
    }

    override fun setRefreshToken(newToken: String) {
        putString(X_REFRESH_TOKEN, newToken)
    }

    override fun resetToken() {
        TODO("Not yet implemented")
    }
    override fun setPermissionRejected(key: String, value: Boolean) {
        putBoolean(key, value)
    }

    override fun getPermissionRejected(key: String): Boolean {
        return getBoolean(key)
    }

    override fun setIsShowedPermissionDialog(key: String, value: Boolean) {
        putBoolean(key, value)
    }

    override fun getIsShowedPermissionDialog(key: String): Boolean {
        return getBoolean(key)
    }

    override fun getIsAlreadyShowedDialog(): Boolean {
        return getBoolean(IS_SHOWED_PERMISSION_DIALOG)
    }

    override fun setIsAlreadyShowedDialog(value: Boolean) {
        putBoolean(IS_SHOWED_PERMISSION_DIALOG, value)
    }
    override fun getIsLogined(): Boolean {
        return getBoolean(IS_LOGINED)
    }

    override fun setIsLogined(value: Boolean) {
        putBoolean(IS_LOGINED, value)
    }


}
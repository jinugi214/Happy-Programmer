package com.gumigames.data.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.gumigames.data.datasource.sharedpreference.PreferenceDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

private const val TAG = "차선호"
fun urlToBitmap(
    imageUrl: String,
    preferenceDataSource: PreferenceDataSource
): Bitmap?{
    val bitmap: Bitmap? = null
    try{
        val url = URL(imageUrl)
//        val stream = url.openStream()
        val connection = url.openConnection() as HttpURLConnection
        connection.setRequestProperty("Authorization", "Bearer ${preferenceDataSource.getAccessToken()}")  // 예시: User-Agent 헤더 추가
        Log.d(TAG, "urlToBitmap에서 accessToken : ${preferenceDataSource.getAccessToken()}")
        val stream = connection.inputStream
        return BitmapFactory.decodeStream(stream)
    }catch (e: Exception){
        e.printStackTrace()
    }
    return bitmap
}


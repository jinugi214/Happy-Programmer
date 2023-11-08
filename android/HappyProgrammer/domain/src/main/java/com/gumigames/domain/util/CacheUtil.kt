package com.gumigames.domain.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

fun urlToBitmap(imageUrl: String): Bitmap?{
    val bitmap: Bitmap? = null
    try{
        val url = URL(imageUrl)
        val stream = url.openStream()
        return BitmapFactory.decodeStream(stream)
    }catch (e: Exception){
        e.printStackTrace()
    }
    return bitmap
}

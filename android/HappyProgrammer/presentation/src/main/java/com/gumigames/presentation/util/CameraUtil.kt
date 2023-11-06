package com.gumigames.presentation.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date

/**
 * 카메라로 찍은 사진을 사진파일로 만듭니다.
 */
fun createImageFile(
    context: Context
): File {
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val storageDir: File =
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!

    return File.createTempFile(
        "JPEG_${timeStamp}_", /* prefix */
        ".jpg", /* suffix */
        storageDir /* directory */
    ).apply {
        // 여기서 absolutePath 변수가 현재 filePath 필요하면 써라
    }
}

/**
 * 만들어진 uri를 파일로 변환합니다
 */
@SuppressLint("Range")
fun uriToFilePath(context: Context, uri: Uri): String? {
    lateinit var filePath: String
    context.contentResolver.query(uri, null, null, null, null).use { cursor ->
        cursor?.let {
            if (it.moveToFirst()) {
                val displayName = it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                val file = File(context.cacheDir, displayName)
                try {
                    val inputStream = context.contentResolver.openInputStream(uri)
                    val outputStream = FileOutputStream(file)
                    inputStream?.copyTo(outputStream)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                filePath = file.absolutePath
            }
        }
    }
    return filePath
}


/**
 * uri로 사진 파일을 가져옵니다
 * createMultipartFromUri로 결과값을 반환합니다
 */
fun getFileFromUri(context: Context, uri: Uri): File? {
    val filePath = uriToFilePath(context, uri)
    return if (filePath != null) File(filePath) else null
}

/**
 * 저장된 사진 파일의 body를 가져옵니다
 */
private fun createRequestBodyFromFile(file: File): RequestBody {
    val MEDIA_TYPE_IMAGE = "multipart/form-data".toMediaTypeOrNull()
    val inputStream: InputStream = FileInputStream(file)
    val byteArray = inputStream.readBytes()
    return RequestBody.create(MEDIA_TYPE_IMAGE, byteArray)
}


/**
 * file로 multipart 객체를 만듭니다.
 */
fun createMultipartFromFile(
    file: File
): MultipartBody.Part{
    val requestFile: RequestBody = createRequestBodyFromFile(file)
    return MultipartBody.Part.createFormData("multipartFiles", file.name, requestFile)
}
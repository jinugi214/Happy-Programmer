package com.gumigames.presentation.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

/**
 * 카메라로 찍은 사진을 사진파일로 만듭니다.
 * openCamera()로 결과를 반환합니다.
 */
fun createImageFile(
    context: Context,
    savePath: (String) -> Unit
): File {
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val storageDir: File =
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!

    return File.createTempFile(
        "JPEG_${timeStamp}_", /* prefix */
        ".jpg", /* suffix */
        storageDir /* directory */
    ).apply {
        // Save a file: path
        savePath(absolutePath)
    }
}

//fun openCamera
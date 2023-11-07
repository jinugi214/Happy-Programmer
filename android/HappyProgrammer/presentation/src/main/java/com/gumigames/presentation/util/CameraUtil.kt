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
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.gumigames.presentation.MainActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date


fun createCameraLauncher(
    fragment: Fragment?,
    activity: MainActivity,
    file: File, //찍은 사진을 담을 파일
    onLoadBitmap: (Bitmap) -> Unit, //찍은 사진을 bitmap으로 반환해서 사용해라
    onSuccess: () -> Unit? //찍은 사진을 가지고 수행할 로직
): ActivityResultLauncher<Intent>{
    val cameraActivityResult =
        (fragment?:activity).registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                var bitmap: Bitmap

                if (Build.VERSION.SDK_INT >= 29) {
                    val source: ImageDecoder.Source = ImageDecoder.createSource(
                        activity.contentResolver,
                        Uri.fromFile(file)
                    )
                    bitmap = ImageDecoder.decodeBitmap(source)

                } else {
                    bitmap = MediaStore.Images.Media.getBitmap(
                        activity.contentResolver,
                        Uri.fromFile(file)
                    )
                }
                onLoadBitmap(bitmap)
                onSuccess()

            }
        }
    return cameraActivityResult
}


/**
 * 카메라 사용할 경우
 * 카메라로 찍은 사진을 저장할 사진파일로 만듭니다.
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


/**
 * 갤러리 사용할 경우
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
 * 갤러리 사용할 경우
 * uri로 사진 파일을 가져옵니다
 * createMultipartFromUri로 결과값을 반환합니다
 */
fun getFileFromUri(context: Context, uri: Uri): File? {
    val filePath = uriToFilePath(context, uri)
    return if (filePath != null) File(filePath) else null
}

/**
 * 갤러리 사용할 경우
 * uri로 multipart 객체를 만듭니다.
 */
fun createMultipartFromUri(context: Context, uri: Uri): MultipartBody.Part? {
    val file: File? = getFileFromUri(context, uri)
    if (file == null) {
        // 파일을 가져오지 못한 경우 처리할 로직을 작성하세요.
        return null
    }

    val requestFile: RequestBody = createRequestBodyFromFile(file)
    return MultipartBody.Part.createFormData("multipartFiles", file.name, requestFile)
}
package com.freeproject.happyprogrammers.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationClass: Application() {

    // 코틀린의 전역변수
    companion object {
        const val CAMERA_PERMISSION_REJECTED = android.Manifest.permission.CAMERA
        const val GALLERY_PERMISSION_REJECTED = android.Manifest.permission.READ_EXTERNAL_STORAGE
        const val IMAGE_PERMISSION_REJECTED = android.Manifest.permission.READ_MEDIA_IMAGES
        val PERMISSION_LIST_UNDER32 = arrayOf(
            CAMERA_PERMISSION_REJECTED,
            GALLERY_PERMISSION_REJECTED
        )
        val PERMISSION_LIST_UP33 = arrayOf(
            CAMERA_PERMISSION_REJECTED,
            IMAGE_PERMISSION_REJECTED
        )
    }

    // 앱이 처음 생성되는 순간, SP를 새로 만들어준다.
    override fun onCreate() {
        super.onCreate()
    }
}
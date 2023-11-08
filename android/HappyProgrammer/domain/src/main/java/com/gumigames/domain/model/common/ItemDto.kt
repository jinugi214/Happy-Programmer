package com.gumigames.domain.model.common

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

data class ItemDto(
    val id: Int,
    val name: String,
    val description: String,
    val imageBitmap: Bitmap,
//    val imgPath: String,
    val isBookmarked: Boolean
)


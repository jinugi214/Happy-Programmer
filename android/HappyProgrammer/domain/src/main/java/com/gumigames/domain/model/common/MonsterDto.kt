package com.gumigames.domain.model.common

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

data class MonsterDto(
    val id: Int,
    val name: String,
    val hp: Int,
    val description: String,
    val imageBitmap: Bitmap?,
    val isBookmarked: Boolean
)

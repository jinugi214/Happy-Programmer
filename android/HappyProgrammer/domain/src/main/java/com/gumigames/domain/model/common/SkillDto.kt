package com.gumigames.domain.model.common

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName


data class SkillDto(
    val id: Int,
    val name: String,
    val description: String,
    val imageBitmap: Bitmap?,
    val isBookmarked: Boolean
)
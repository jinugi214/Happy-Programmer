package com.gumigames.data.mapper

import android.util.Log
import com.gumigames.data.BuildConfig
import com.gumigames.data.datasource.entity.UserEntity
import com.gumigames.data.datasource.sharedpreference.PreferenceDataSource
import com.gumigames.data.model.request.user.LoginRequest
import com.gumigames.data.model.response.user.UserInfoResponse
import com.gumigames.data.util.urlToBitmap
import com.gumigames.domain.model.user.LoginDto
import com.gumigames.domain.model.user.UserInfoDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "차선호"

fun LoginDto.toData(): LoginRequest{
    return LoginRequest(
        username = id,
        password = password
    )
}

suspend fun UserInfoResponse.toDomain(preferenceDataSource: PreferenceDataSource): UserInfoDto{
    return UserInfoDto(
        id = id,
        exp = exp,
        gender = gender,
        imageBitmap = withContext(Dispatchers.IO) {
            Log.d(TAG, "bitmap 결과 ${urlToBitmap(
                imageUrl = BuildConfig.BASE_URL + imgPath,
                preferenceDataSource = preferenceDataSource
            )!!}")
            urlToBitmap(
                imageUrl = BuildConfig.BASE_URL + imgPath,
                preferenceDataSource = preferenceDataSource
            )!!
        },
        level = level,
        name = name,
        point = point,
        savepoint = savepoint,
        storyProgress = storyProgress
    )
}

fun UserInfoDto.toData(): UserEntity {
    return UserEntity(
        id = id,
        exp = exp,
        gender = gender,
        imageBitmap = imageBitmap,
        level = level,
        name = name,
        point = point,
        savepoint = savepoint,
        storyProgress = storyProgress
    )
}

fun UserEntity.toDomain(): UserInfoDto{
    return UserInfoDto(
        id = id,
        exp = exp,
        gender = gender,
        imageBitmap = imageBitmap,
        level = level,
        name = name,
        point = point,
        savepoint = savepoint,
        storyProgress = storyProgress
    )
}
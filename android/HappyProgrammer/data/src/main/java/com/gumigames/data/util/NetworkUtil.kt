package com.gumigames.data.util

import android.util.Log
import com.gumigames.domain.util.getValueOrThrow
import org.json.JSONObject
import retrofit2.Response


internal inline fun <T> handleApi(block: () -> Response<T>): T {
    return block.invoke().getValueOrThrow()
}
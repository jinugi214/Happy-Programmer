package com.gumigames.data.util

import com.gumigames.domain.util.getNetworkResult
import retrofit2.Response


private const val TAG = "차선호"

internal inline fun <T> handleApi(block: () -> Response<T>): T {
    return block.invoke().getNetworkResult()
}


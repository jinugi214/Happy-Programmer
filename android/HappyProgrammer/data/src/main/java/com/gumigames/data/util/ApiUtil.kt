package com.gumigames.data.util

import com.gumigames.domain.util.getValueOrThrow
import retrofit2.Response


internal inline fun <T> handleApi(block: () -> Response<T>): T {
    return block.invoke().getValueOrThrow()
}
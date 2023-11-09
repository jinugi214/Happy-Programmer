package com.gumigames.domain.util

import android.util.Log
import org.json.JSONObject
import retrofit2.Response
import java.net.UnknownHostException

private const val TAG = "차선호"
private fun <T> Response<T>.isDelete(): Boolean {
    return this.raw().request.method == "DELETE"
}

/**
 * Data Layer에서 Network 결과를 처리할 함수
 */
@Suppress("UNCHECKED_CAST")
fun <T> Response<T>.getNetworkResult(): T {

    if (this.isSuccessful) {
        if (this.isDelete()) { return Unit as T }
        return this.body() ?: throw NetworkThrowable.IllegalStateThrowable()
    }

    Log.d(TAG, "getNetworkResult not successful : $this")
    Log.d(TAG, "getNetworkResult not successful : ${this.errorBody()?.string()}")

    // TODO 서버에 따라 다를수도?
//    val errorResponse = errorBody()?.string()
//    val jsonObject = errorResponse?.let { JSONObject(it) }
//    val code = jsonObject?.getInt("code") ?: 0
//    val message = jsonObject?.getString("message") ?: ""

    //Github API에서는 단순히 Response{protocol=h2, code=404, message=, url=https://api.github.com/users//repos} 이렇게 반환해줌
    val code = this.code()
    val message = this.errorBody()?.string() ?: ""



    Log.e(TAG, "getNetworkResult: Error code : ${code}, message : ${message}")

    when (code) {
        in 100..199 -> { throw NetworkThrowable.Base100Throwable(code, message) }
        in 300..399 -> { throw NetworkThrowable.Base300Throwable(code, message) }
        in 400..499 -> { throw NetworkThrowable.Base400Throwable(code, message) }
        in 500..599 -> { throw NetworkThrowable.Base500Throwable(code, message) }
    }

    throw NetworkThrowable.IllegalStateThrowable()
}

/**
 * throwable 처리하기 전까지 가져갈 함수
 */
suspend fun <T> getValueOrThrow(block: suspend () -> T): T{
    try{
        return block()
    }catch (throwable: NetworkThrowable){
        Log.d(TAG, "getValueOrThrow : $throwable")
        throw throwable
    }
}


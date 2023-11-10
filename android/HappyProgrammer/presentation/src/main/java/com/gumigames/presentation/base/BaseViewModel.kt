package com.gumigames.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gumigames.domain.util.NetworkThrowable
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.math.log

private const val TAG = "차선호"
abstract class BaseViewModel: ViewModel() {

    private val _error = MutableSharedFlow<Throwable>()
    var error = _error.asSharedFlow()

    private val _isExpiredRefreshToken = MutableStateFlow(false)
    var isExpiredRefreshToken = _isExpiredRefreshToken.asStateFlow()

    fun <T> getApiResult(
        block: suspend () -> T, //실행할 함수
        success: suspend (T) -> Unit //성공했을 때 실행할 함수
    ){
        viewModelScope.launch {
            try {
                val response = block()
                success(response)
            }catch (e: IOException){
                Log.d(TAG, "getApiResult error : ${e.cause}")
            }catch (throwable: Throwable){
                Log.d(TAG, "getApiResult throwable : $throwable")
                if (throwable is NetworkThrowable) {
                    if(throwable.message == NetworkThrowable.REFRESH_EXPIRE_MESSAGE){
                        _isExpiredRefreshToken.emit(true)
                    }else{
                        _error.emit(throwable)
                    }
                }else {
                    _error.emit(NetworkThrowable.NetworkErrorThrowable())
                }
            }
        }
    }

}
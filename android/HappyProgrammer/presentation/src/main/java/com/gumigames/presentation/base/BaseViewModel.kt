package com.gumigames.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gumigames.domain.util.NetworkThrowable
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {

    private val _error = MutableSharedFlow<Throwable>()
    var error = _error.asSharedFlow()

    fun <T> getApiResult(
        block: suspend () -> T, //실행할 함수
        success: suspend (T) -> Unit //성공했을 때 실행할 함수
    ){
        viewModelScope.launch {
            try {
                success(block())
            }catch (throwable: Throwable){
                if (throwable is NetworkThrowable) {
                    _error.emit(throwable)
                } else {
                    _error.emit(NetworkThrowable.NetworkErrorThrowable())
                }
            }
        }
    }
}
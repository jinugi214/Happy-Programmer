package com.gumigames.presentation.ui.mission

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gumigames.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class MissionViewModel @Inject constructor(

): BaseViewModel() {

    private val _multipartBody = MutableSharedFlow<MultipartBody.Part>()
    var multipartBody = _multipartBody.asSharedFlow()
    fun setMultipartBody(
        multipartBody: MultipartBody.Part?,
        onFail: () -> Unit
    ){
        viewModelScope.launch {
            if(multipartBody == null){
                onFail()
            }else {
                _multipartBody.emit(multipartBody)
            }
        }
    }


}
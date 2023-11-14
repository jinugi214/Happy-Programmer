package com.gumigames.presentation.ui.mission

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gumigames.domain.usecase.mission.GetMissionPhotoResultUseCase
import com.gumigames.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

private const val TAG = "차선호"
@HiltViewModel
class MissionViewModel @Inject constructor(
    private val getMissionPhotoResultUseCase: GetMissionPhotoResultUseCase
): BaseViewModel() {

    private val _multipartBody =  MutableStateFlow<MultipartBody.Part?>(null)
    var multipartBody = _multipartBody.asStateFlow()
    fun isPossibleSendPhoto(): Boolean{
        return multipartBody.value!=null
    }
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


    private val _resultMessage = MutableSharedFlow<String>()
    val resultMessage = _resultMessage.asSharedFlow()
    fun sendPhoto(){
        viewModelScope.launch {
            Log.d(TAG, "sendPhoto multipartBody : ${_multipartBody.value!!}")
            _resultMessage.emit(getMissionPhotoResultUseCase.invoke(_multipartBody.value!!).result)
        }
    }


}
package com.gumigames.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gumigames.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

): BaseViewModel() {

    private var _id: String = ""
    fun setId(id: String){
        _id = id
    }
    fun getId(): String{
        return _id
    }

    private var _pw: String = ""
    fun setPw(pw: String){
        _pw = pw
    }
    fun getPw(): String{
        return _pw
    }

    //아이디, 비밀번호 확인 결과
    private val _loginResult = MutableSharedFlow<Boolean>()
    var loginResult = _loginResult.asSharedFlow()
    //로그인 시도
    fun login(){
        viewModelScope.launch {
            _loginResult.emit(true)
        }
    }

    //사용자 정보 조회 상태
    private val _isBroughtUserInfo = MutableSharedFlow<Boolean>()
    var isBroughtUserInfo = _isBroughtUserInfo.asSharedFlow()
    //사용자 정보 조회 시작
    fun bringUserInfo(){
        viewModelScope.launch {
            delay(3000)
            _isBroughtUserInfo.emit(true)
        }
    }

}
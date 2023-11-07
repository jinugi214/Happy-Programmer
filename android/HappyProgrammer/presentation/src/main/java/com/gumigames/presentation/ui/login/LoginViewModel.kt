package com.gumigames.presentation.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gumigames.domain.model.user.LoginDto
import com.gumigames.domain.usecase.preference.SetAccessTokenUseCase
import com.gumigames.domain.usecase.preference.SetIsLoginedUseCase
import com.gumigames.domain.usecase.preference.SetRefreshTokenUseCase
import com.gumigames.domain.usecase.user.LoginUseCase
import com.gumigames.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "차선호"
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val setAccessTokenUseCase: SetAccessTokenUseCase,
    private val setRefreshTokenUseCase: SetRefreshTokenUseCase,
    private val setIsLoginedUseCase: SetIsLoginedUseCase
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
            Log.d(TAG, "login... id : $_id / pw : $_pw")
            getApiResult(block = {loginUseCase.invoke(LoginDto(id = _id, password = _pw))}){
                setAccessTokenUseCase.invoke(it.accessToken)
                setRefreshTokenUseCase.invoke(it.refreshToken)
                _loginResult.emit(true)
            }
        }
    }

    //사용자 정보 조회 상태
    private val _isBroughtUserInfo = MutableSharedFlow<Boolean>()
    var isBroughtUserInfo = _isBroughtUserInfo.asSharedFlow()
    //사용자 정보 조회 시작
    fun bringUserInfo(){
        viewModelScope.launch {
            //TODO 회원의 즐겨찾기 목록 불러와서 room에 저장해줘야 함
            delay(300)
            setIsLoginedUseCase.invoke(true)
            _isBroughtUserInfo.emit(true)
        }
    }

}
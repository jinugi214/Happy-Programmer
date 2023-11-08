package com.gumigames.presentation.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gumigames.domain.model.user.LoginDto
import com.gumigames.domain.usecase.dogam.litem.GetAllItemsLocalUseCase
import com.gumigames.domain.usecase.dogam.litem.GetAllItemsUseCase
import com.gumigames.domain.usecase.dogam.litem.InsertAllItemsLocalUseCase
import com.gumigames.domain.usecase.dogam.monster.GetAllMonstersLocalUseCase
import com.gumigames.domain.usecase.dogam.monster.GetAllMonstersUseCase
import com.gumigames.domain.usecase.dogam.monster.InsertAllMonstersLocalUseCase
import com.gumigames.domain.usecase.dogam.skill.GetAllSkillsLocalUseCase
import com.gumigames.domain.usecase.dogam.skill.GetAllSkillsUseCase
import com.gumigames.domain.usecase.dogam.skill.InsertAllSkillsLocalUseCase
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
    private val setIsLoginedUseCase: SetIsLoginedUseCase,
    private val getAllItemsUseCase: GetAllItemsUseCase,
    private val insertAllItemsLocalUseCase: InsertAllItemsLocalUseCase,
    private val getAllSkillsUseCase: GetAllSkillsUseCase,
    private val insertAllSkillsLocalUseCase: InsertAllSkillsLocalUseCase,
    private val getAllMonstersUseCase: GetAllMonstersUseCase,
    private val insertAllMonstersLocalUseCase: InsertAllMonstersLocalUseCase
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
    private val _isBroughtGameInfo = MutableSharedFlow<Boolean>()
    var isBroughtGameInfo = _isBroughtGameInfo.asSharedFlow()

    //회원, 아이템, 스킬, 몬스터 정보 조회 상태
    private var _isBroughtUserInfo = false
    private var _isBroughtItemsInfo = false
    private var _isBroughtSkillsInfo = false
    private var _isBroughtMonstersInfo = false

    fun bringGameInfo(){
        viewModelScope.launch {
            //TODO 유저도 추가해야 함
            getApiResult(block = {getAllItemsUseCase.invoke()}){
                insertAllItemsLocalUseCase.invoke(it)
                _isBroughtItemsInfo = true
                if(checkGetAllGameInfo()) {
                    _isBroughtGameInfo.emit(true)
                    setIsLoginedUseCase.invoke(true)
                }
            }
            getApiResult(block = {getAllSkillsUseCase.invoke()}){
                insertAllSkillsLocalUseCase.invoke(it)
                _isBroughtSkillsInfo = true
                if(checkGetAllGameInfo()) {
                    _isBroughtGameInfo.emit(true)
                    setIsLoginedUseCase.invoke(true)
                }
            }
            getApiResult(block = {getAllMonstersUseCase.invoke()}){
                insertAllMonstersLocalUseCase.invoke(it)
                _isBroughtMonstersInfo = true
                if(checkGetAllGameInfo()) {
                    _isBroughtGameInfo.emit(true)
                    setIsLoginedUseCase.invoke(true)
                }
            }
        }
    }

    fun checkGetAllGameInfo(): Boolean{
        //TODO 사용자 정보 조회도 추가
        return _isBroughtItemsInfo && _isBroughtSkillsInfo && _isBroughtMonstersInfo
    }

}
package com.gumigames.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.gumigames.domain.model.user.UserInfoDto
import com.gumigames.domain.usecase.dogam.litem.GetAllItemsUseCase
import com.gumigames.domain.usecase.dogam.litem.InsertAllItemsLocalUseCase
import com.gumigames.domain.usecase.dogam.monster.GetAllMonstersUseCase
import com.gumigames.domain.usecase.dogam.monster.InsertAllMonstersLocalUseCase
import com.gumigames.domain.usecase.dogam.skill.GetAllSkillsUseCase
import com.gumigames.domain.usecase.dogam.skill.InsertAllSkillsLocalUseCase
import com.gumigames.domain.usecase.preference.GetIsLoginedUseCase
import com.gumigames.domain.usecase.preference.GetIsShowedPermissionDialogUseCase
import com.gumigames.domain.usecase.preference.GetPermissionRejectedUseCase
import com.gumigames.domain.usecase.preference.SetIsAlreadyShowedDialogUseCase
import com.gumigames.domain.usecase.preference.SetIsLoginedUseCase
import com.gumigames.domain.usecase.preference.SetIsShowedPermissionDialogUseCase
import com.gumigames.domain.usecase.preference.SetPermissionRejectedUseCase
import com.gumigames.domain.usecase.user.GetUserInfoLocalUseCase
import com.gumigames.domain.usecase.user.GetUserInfoUseCase
import com.gumigames.domain.usecase.user.InsertUserInfoLocalUseCase
import com.gumigames.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "차선호"
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPermissionRejectedUseCase: GetPermissionRejectedUseCase,
    private val setPermissionRejectedUseCase: SetPermissionRejectedUseCase,
    private val getIsShowedPermissionDialogUseCase: GetIsShowedPermissionDialogUseCase,
    private val setIsShowedPermissionDialogUseCase: SetIsShowedPermissionDialogUseCase,
    private val setIsAlreadyShowedDialogUseCase: SetIsAlreadyShowedDialogUseCase,
    private val getIsLoginedUseCase: GetIsLoginedUseCase,
    private val setIsLoginedUseCase: SetIsLoginedUseCase,
    private val getAllItemsUseCase: GetAllItemsUseCase,
    private val insertAllItemsLocalUseCase: InsertAllItemsLocalUseCase,
    private val getAllSkillsUseCase: GetAllSkillsUseCase,
    private val insertAllSkillsLocalUseCase: InsertAllSkillsLocalUseCase,
    private val getAllMonstersUseCase: GetAllMonstersUseCase,
    private val insertAllMonstersLocalUseCase: InsertAllMonstersLocalUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getUserInfoLocalUseCase: GetUserInfoLocalUseCase,
    private val insertUserInfoLocalUseCase: InsertUserInfoLocalUseCase,
): BaseViewModel() {

    fun isLogined(): Boolean{
        return getIsLoginedUseCase.invoke()
    }

    ////////////////////// 권한 관리 ///////////////////////////////
    private var _isShowPermissionDialog: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isShowPermissionDialog: StateFlow<Boolean>
        get() = _isShowPermissionDialog.asStateFlow()
    fun getPermissionRejected(key: String): Boolean{
        return getPermissionRejectedUseCase.invoke(key)
    }
    fun setPermissionRejected(key: String){
        setPermissionRejectedUseCase.invoke(key, true)
    }
    fun getIsShowedPermissionDialog(key: String): Boolean{
        return getIsShowedPermissionDialogUseCase.invoke(key)
    }
    fun setIsShowedPermissionDialog(key:String){
       setIsShowedPermissionDialogUseCase.invoke(key, true)
    }
    fun setIsAlreadyShowedDialog(value: Boolean){
        setIsAlreadyShowedDialogUseCase.invoke(value)
    }
    fun setIsShowPermissionDialog(value: Boolean){
        viewModelScope.launch {
            Log.d(TAG, "setIsShowPermissionDialog true로 변경")
            _isShowPermissionDialog.emit(value)
        }
    }


    //게임 정보 조회
    //게임 정보 조회 상태
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
            getApiResult(block = {getUserInfoUseCase.invoke()}){
                insertUserInfoLocalUseCase.invoke(it)
                Log.d(TAG, "bringGameInfo에서 user 정보 조회 성공")
                _isBroughtUserInfo = true
                if(checkGetAllGameInfo()) {
                    _isBroughtGameInfo.emit(true)
                    setIsLoginedUseCase.invoke(true)
                }
            }
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
        return _isBroughtUserInfo && _isBroughtItemsInfo && _isBroughtSkillsInfo && _isBroughtMonstersInfo
    }

    ////////////////////////////////////////// 유저 //////////////////////////////////////////////
    private var _userInfo = MutableStateFlow<UserInfoDto?>(null)
    val userInfo = _userInfo.asStateFlow()
    fun getUserInfo(){
        viewModelScope.launch {
            getApiResult(block = {getUserInfoLocalUseCase.invoke()}){
                _userInfo.emit(it)
            }
        }
    }
}
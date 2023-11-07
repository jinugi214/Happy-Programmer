package com.gumigames.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.gumigames.domain.usecase.preference.GetIsLoginedUseCase
import com.gumigames.domain.usecase.preference.GetIsShowedPermissionDialogUseCase
import com.gumigames.domain.usecase.preference.GetPermissionRejectedUseCase
import com.gumigames.domain.usecase.preference.SetIsAlreadyShowedDialogUseCase
import com.gumigames.domain.usecase.preference.SetIsShowedPermissionDialogUseCase
import com.gumigames.domain.usecase.preference.SetPermissionRejectedUseCase
import com.gumigames.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

}
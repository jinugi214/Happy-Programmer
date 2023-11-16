package com.gumigames.presentation.ui.setting

import com.gumigames.domain.usecase.setting.LogoutUseCase
import com.gumigames.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase
): BaseViewModel() {

    private val _isLogouted = MutableSharedFlow<Boolean>()
    val isLogouted = _isLogouted.asSharedFlow()
    suspend fun logout(){
        logoutUseCase.invoke()
        _isLogouted.emit(true)
    }
}
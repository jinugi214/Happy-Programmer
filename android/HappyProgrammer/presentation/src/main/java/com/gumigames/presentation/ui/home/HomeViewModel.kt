package com.gumigames.presentation.ui.home

import com.gumigames.domain.usecase.user.GetUserInfoLocalUseCase
import com.gumigames.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserInfoLocalUseCase: GetUserInfoLocalUseCase
): BaseViewModel() {

    private var _isConnected = false
    fun setIsConnected(value: Boolean){
        _isConnected = value
    }
    fun getIsConnected(): Boolean{
        return _isConnected
    }
}
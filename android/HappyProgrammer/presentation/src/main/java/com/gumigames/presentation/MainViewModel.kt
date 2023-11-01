package com.gumigames.presentation

import androidx.lifecycle.ViewModel
import com.gumigames.domain.usecase.login.GetIsLoginedUseCase
import com.gumigames.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getIsLoginedUseCase: GetIsLoginedUseCase
): BaseViewModel() {

    fun isLogined(): Boolean{
        return getIsLoginedUseCase.invoke()
    }


}
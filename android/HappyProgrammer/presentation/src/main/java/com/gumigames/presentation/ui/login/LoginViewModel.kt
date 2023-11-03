package com.gumigames.presentation.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

): ViewModel() {

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

}
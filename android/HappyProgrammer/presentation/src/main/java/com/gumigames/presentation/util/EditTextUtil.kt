package com.gumigames.presentation.util

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


//값 입력할 때마다 불릴 함수
fun EditText.setTextListener(
    setText: (String) -> Unit
){
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(value: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(value: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(value: Editable?) {
            setText(value.toString())
        }
    })
}

//focus 리스너 함수
fun EditText.setFocusListener(
    onFocus: () -> Unit,
    onClear: () -> Unit
){
    onFocusChangeListener = OnFocusChangeListener { _, hasFoucs ->
        if(hasFoucs){
            onFocus()
        }else{
            onClear()
        }
    }
}

//엔터 누를 때 실행될 함수
fun EditText.clickEnterListener(
    onClick: () -> Unit
){
    setOnKeyListener { _, keyCode, event ->
        if(event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
            onClick()
            true
        }else{
            false
        }
    }
}

//키보드 숨기기
fun hideKeyboard(activity: Activity){
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(activity.window.decorView.applicationWindowToken, 0)
}

//키보드 올리기
fun showKeyboard(activity: Activity, editText: EditText){
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(editText, 0)
}
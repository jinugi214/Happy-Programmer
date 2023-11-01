package com.gumigames.presentation.util

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.widget.EditText

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
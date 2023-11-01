package com.gumigames.presentation.ui.skill

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SkillViewModel @Inject constructor(

): ViewModel() {

    private var _searchTextList = MutableSharedFlow<List<String>>()
    val searchTextList = _searchTextList.asSharedFlow()

    fun setSearchTextList(list: List<String>){
        viewModelScope.launch {
            _searchTextList.emit(list)
        }
    }

}
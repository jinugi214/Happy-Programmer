package com.gumigames.presentation.ui.common.item

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.repository.DogamRepository
import com.gumigames.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "차선호"
@HiltViewModel
class ItemDetailDialogViewModel @Inject constructor(
    private val dogamRepository: DogamRepository
): BaseViewModel(){

    private var _currentItem: ItemDto? = null
    fun setCurrentItem(itemDto: ItemDto){
        _currentItem = itemDto
    }

    fun getCurrentItem(): ItemDto{
        return _currentItem ?: ItemDto(-1,"","",null,false)
    }

    private val _currentIsBookmarked = MutableSharedFlow<Boolean>()
    val currentIsBookmarked = _currentIsBookmarked.asSharedFlow()
    fun setCurrentIsBookmarked(value: Boolean){
        viewModelScope.launch {
            _currentIsBookmarked.emit(value)
        }
    }

    fun toggleIsBookmarked(){
        Log.d(TAG, "toggleIsBookmarked item id : ${getCurrentItem().id} ")
        getApiResult(block = {dogamRepository.toggleBookmarkItem(getCurrentItem().id)}){
            _currentIsBookmarked.emit(it)
        }
    }
}
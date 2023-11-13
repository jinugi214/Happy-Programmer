package com.gumigames.presentation.ui.common.item

import android.content.ClipData.Item
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.repository.DogamRepository
import com.gumigames.domain.usecase.dogam.litem.AddBookmarkItemLocalUseCase
import com.gumigames.domain.usecase.dogam.litem.DeleteBookmarkItemLocalUseCase
import com.gumigames.domain.usecase.dogam.litem.ToggleBookmarkItemUseCase
import com.gumigames.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "차선호"
@HiltViewModel
class ItemDetailDialogViewModel @Inject constructor(
    private val toggleBookmarkItemUseCase: ToggleBookmarkItemUseCase,
    private val addBookmarkItemLocalUseCase: AddBookmarkItemLocalUseCase,
    private val deleteBookmarkItemLocalUseCase: DeleteBookmarkItemLocalUseCase
): BaseViewModel(){

    private var _currentItem: ItemDto? = null
    fun setCurrentItem(itemDto: ItemDto){
        _currentItem = itemDto
    }

    fun getCurrentItem(): ItemDto{
        return _currentItem ?: ItemDto(-1,"","",null, false, false)
    }

    private val _currentIsBookmarked = MutableSharedFlow<Boolean>()
    val currentIsBookmarked = _currentIsBookmarked.asSharedFlow()
    fun setCurrentIsBookmarked(value: Boolean){
        viewModelScope.launch {
            _currentIsBookmarked.emit(value)
        }
    }

    fun toggleIsBookmarked(){
        viewModelScope.launch {
            Log.d(TAG, "toggleIsBookmarked item id : ${getCurrentItem().id} ")
            getApiResult(block = {toggleBookmarkItemUseCase.invoke(getCurrentItem().id)}){
                _currentIsBookmarked.emit(it)
                if(it){ //로컬에 isBookmarked true로 갱신
                    addBookmarkItemLocalUseCase.invoke(getCurrentItem().id)
                }else{ //로컬에 isBookmarked false로 갱신
                    deleteBookmarkItemLocalUseCase.invoke(getCurrentItem().id)
                }
            }

        }
    }

    fun updateDogamList(
        value: Boolean,
        list: List<ItemDto>,
        position: Int,
        onUpdate: (List<ItemDto>) -> Unit
    ){
        if(value) {
            val newItemList = list.map { itemDto -> itemDto.copy() } //각 객체들도 깊은 복사 필수
            newItemList[position].isBookmarked = true
            onUpdate(newItemList)
        }else{
            val newItemList = list.map { itemDto ->  itemDto.copy() } //각 객체들도 깊은 복사 필수
            newItemList[position].isBookmarked = false
            onUpdate(newItemList)
        }
    }

    fun updateBookmarkList(
        value: Boolean,
        list: List<ItemDto>,
        position: Int,
        item: ItemDto?,
        onUpdate: (List<ItemDto>) -> Unit
    ){
        if(value){
            val newItemList = list.map { itemDto ->  itemDto.copy() }.toMutableList() //각 객체들도 깊은 복사 필수
            newItemList.add(position, item!!)
            onUpdate(newItemList)
        }else{
            val newItemList = list.filterIndexed { index, _ -> index != position }.map { itemDto ->  itemDto.copy() } //각 객체들도 깊은 복사 필수
            onUpdate(newItemList)
        }
    }

}
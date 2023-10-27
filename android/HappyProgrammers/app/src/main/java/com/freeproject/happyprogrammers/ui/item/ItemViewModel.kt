package com.freeproject.happyprogrammers.ui.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freeproject.happyprogrammers.R
import com.freeproject.happyprogrammers.data.model.ItemDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(

): ViewModel() {

    private var _currentItemList = MutableSharedFlow<List<ItemDto>>()

    val currentItemList: SharedFlow<List<ItemDto>>
        get() = _currentItemList.asSharedFlow()

    fun getTotalItemList(){
        //TODO 원래라면 Repository를 통해 전체 아이템 조회
        viewModelScope.launch {
            _currentItemList.emit(
                listOf(
                    ItemDto(id = 0, imageUrl = R.drawable.image_keyboard1),
                    ItemDto(id = 1, imageUrl = R.drawable.image_keyboard2),
                    ItemDto(id = 2, imageUrl = R.drawable.image_keyboard3),
                    ItemDto(id = 3, imageUrl = R.drawable.image_mouse1),
                    ItemDto(id = 4, imageUrl = R.drawable.image_mouse2),
                    ItemDto(id = 10, imageUrl = R.drawable.image_phone1),
                    ItemDto(id = 11, imageUrl = R.drawable.image_phone2),
                    ItemDto(id = 12, imageUrl = R.drawable.image_headphone)
                )
            )
        }
    }

    fun getKeyboardItemList(){
        //TODO 원래라면 Repository를 통해 키보드 아이템 조회
        viewModelScope.launch {
            _currentItemList.emit(
                listOf(
                    ItemDto(id = 5, imageUrl = R.drawable.image_keyboard1),
                    ItemDto(id = 6, imageUrl = R.drawable.image_keyboard2),
                    ItemDto(id = 7, imageUrl = R.drawable.image_keyboard3)
                )
            )
        }
    }

    fun getMouseItemList(){
        //TODO 원래라면 Repository를 통해 마우스 아이템 조회
        viewModelScope.launch {
            _currentItemList.emit(
                listOf(
                    ItemDto(id = 8, imageUrl = R.drawable.image_mouse1),
                    ItemDto(id = 9, imageUrl = R.drawable.image_mouse2),
                    ItemDto(id = 9, imageUrl = R.drawable.image_mouse3)
                )
            )
        }
    }

    fun getEtcItemList(){
        //TODO 원래라면 Repository를 통해 기타 아이템 조회
        viewModelScope.launch {
            _currentItemList.emit(
                listOf(
                    ItemDto(id = 13, imageUrl = R.drawable.image_phone1),
                    ItemDto(id = 14, imageUrl = R.drawable.image_phone2),
                    ItemDto(id = 15, imageUrl = R.drawable.image_headphone)
                )
            )
        }
    }

    //아이템 클릭 리스너 통제 변수
    private val _itemClickListenerEnabled = AtomicBoolean(true) // 플래그 변수 생성 및 초기화
    fun getItemClickListenerEnabled(): Boolean{
        return _itemClickListenerEnabled.get()
    }
    fun setItemClickListenerEnabled(value: Boolean){
        _itemClickListenerEnabled.set(value)
    }

}
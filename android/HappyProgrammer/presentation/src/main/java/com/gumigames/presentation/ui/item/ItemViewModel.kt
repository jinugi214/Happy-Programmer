package com.gumigames.presentation.ui.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gumigames.domain.model.item.ItemDto
import com.gumigames.domain.usecase.litem.GetAllItemsUseCase
import com.gumigames.domain.usecase.litem.GetSearchItemsUseCase
import com.gumigames.presentation.R
import com.gumigames.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val getAllItemsUseCase: GetAllItemsUseCase,
    private val getSearchItemsUseCase: GetSearchItemsUseCase
): BaseViewModel() {

    //현재 아이템 리스트
    private var _currentItemList = MutableSharedFlow<List<ItemDto>>()

    val currentItemList: SharedFlow<List<ItemDto>>
        get() = _currentItemList.asSharedFlow()

    fun getAllItems(){
        //TODO 원래라면 Repository를 통해 전체 아이템 조회
        viewModelScope.launch {
            getApiResult( block = {getAllItemsUseCase.invoke()}){
                _currentItemList.emit(it)
            }
        }
    }

    //현재 선택된 아이템
    private var _selectedItem = MutableStateFlow<ItemDto?>(null)
    val selectedItem = _selectedItem.asStateFlow()
    fun setSelectedItem(item: ItemDto?){
        viewModelScope.launch {
            _selectedItem.emit(item)
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

    //검색 키워드
    private var _searchKeyword: String = ""
    fun setSearchKeyword(keyword: String){
        _searchKeyword = keyword
    }

    fun getSearchItems(){
        viewModelScope.launch {
            if(_searchKeyword==""){ //아무것도 입력 안하면 전체 아이템 조회
                getApiResult(block = {getAllItemsUseCase.invoke()}){
                    _currentItemList.emit(it)
                }
            }else{
                getApiResult(block = {getSearchItemsUseCase.invoke(_searchKeyword)}){
                    _currentItemList.emit(it)
                }
            }
        }
    }

}
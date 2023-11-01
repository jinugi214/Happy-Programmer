package com.gumigames.presentation.ui.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gumigames.domain.model.item.ItemDto
import com.gumigames.domain.usecase.litem.GetAllItemsUseCase
import com.gumigames.presentation.R
import com.gumigames.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val getAllItemsUseCase: GetAllItemsUseCase
): BaseViewModel() {

    private var _currentItemList = MutableSharedFlow<List<ItemDto>>()

    val currentItemList: SharedFlow<List<ItemDto>>
        get() = _currentItemList.asSharedFlow()

    fun getTotalItemList(){
        //TODO 원래라면 Repository를 통해 전체 아이템 조회
        viewModelScope.launch {
            getApiResult( block = {getAllItemsUseCase.invoke()}){
                _currentItemList.emit(it)
            }
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
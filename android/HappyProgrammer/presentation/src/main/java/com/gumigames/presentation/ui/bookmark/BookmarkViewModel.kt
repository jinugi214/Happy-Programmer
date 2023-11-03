package com.gumigames.presentation.ui.bookmark

import androidx.lifecycle.viewModelScope
import com.gumigames.domain.model.item.ItemDto
import com.gumigames.domain.model.item.SkillDto
import com.gumigames.domain.usecase.bookmark.item.AddBookmarkItemLocalUseCase
import com.gumigames.domain.usecase.bookmark.item.GetAllBookmarkItemsLocalUseCase
import com.gumigames.domain.usecase.bookmark.skill.AddBookmarkSkillLocalUseCase
import com.gumigames.domain.usecase.bookmark.skill.GetAllBookmarkSkillsLocalUseCase
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

private const val TAG = "차선호"
@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getAllBookmarkItemsLocalUseCase: GetAllBookmarkItemsLocalUseCase,
    private val addBookmarkItemLocalUseCase: AddBookmarkItemLocalUseCase,
    private val getAllBookmarkSkillsLocalUseCase: GetAllBookmarkSkillsLocalUseCase,
    private val addBookmarkSkillLocalUseCase: AddBookmarkSkillLocalUseCase

): BaseViewModel(){

    ////////////////////////////////////////////////// 공통 ////////////////////////////////////////////////////

    //현재 탭
    private var _currentTab: String = "item"
    fun setCurrentTab(tab: String){
        _currentTab = tab
    }

    //아이템 클릭 리스너 통제 변수
    private val _itemClickListenerEnabled = AtomicBoolean(true) // 플래그 변수 생성 및 초기화
    fun getItemClickListenerEnabled(): Boolean{
        return _itemClickListenerEnabled.get()
    }
    fun setItemClickListenerEnabled(value: Boolean){
        _itemClickListenerEnabled.set(value)
    }

    ///////////////////////////////////////////////// 아이템 /////////////////////////////////////////////////////

    //현재 아이템 북마크 리스트
    private var _currentBookmarkItemList = MutableSharedFlow<List<ItemDto>>()

    val currentBookmarkItemList: SharedFlow<List<ItemDto>>
        get() = _currentBookmarkItemList.asSharedFlow()

    //현재 선택된 아이템
    private var _selectedBookmarkItem = MutableStateFlow<ItemDto?>(null)
    val selectedBookmarkItem = _selectedBookmarkItem.asStateFlow()
    fun setSelectedBookmarkItem(item: ItemDto?){
        viewModelScope.launch {
            _selectedBookmarkItem.emit(item)
        }
    }

    //전체 아이템 조회
    fun getAllBookmarkItemsLocal(){
        viewModelScope.launch {
            _currentBookmarkItemList.emit(getAllBookmarkItemsLocalUseCase.invoke())
        }
    }

    ///////////////////////////////////////////////// 스킬 /////////////////////////////////////////////////////

    //현재 아이템 북마크 리스트
    private var _currentBookmarkSkillList = MutableSharedFlow<List<SkillDto>>()

    val currentBookmarkSkillList: SharedFlow<List<SkillDto>>
        get() = _currentBookmarkSkillList.asSharedFlow()

    //현재 선택된 아이템
    private var _selectedBookmarkSkill = MutableStateFlow<SkillDto?>(null)
    val selectedBookmarkSkill = _selectedBookmarkSkill.asStateFlow()
    fun setSelectedBookmarkSkill(skill: SkillDto?){
        viewModelScope.launch {
            _selectedBookmarkSkill.emit(skill)
        }
    }

    //전체 아이템 조회
    fun getAllBookmarkSkillsLocal(){
        viewModelScope.launch {
            _currentBookmarkSkillList.emit(getAllBookmarkSkillsLocalUseCase.invoke())
        }
    }

}
package com.gumigames.presentation.ui.bookmark

import androidx.lifecycle.viewModelScope
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.model.common.SkillDto
import com.gumigames.domain.usecase.dogam.litem.AddBookmarkItemLocalUseCase
import com.gumigames.domain.usecase.dogam.litem.GetAllBookmarkItemsLocalUseCase
import com.gumigames.domain.usecase.dogam.monster.AddBookmarkMonsterLocalUseCase
import com.gumigames.domain.usecase.dogam.monster.GetAllBookmarkMonstersLocalUseCase
import com.gumigames.domain.usecase.dogam.skill.AddBookmarkSkillLocalUseCase
import com.gumigames.domain.usecase.dogam.skill.GetAllBookmarkSkillsLocalUseCase
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
    private val addBookmarkSkillLocalUseCase: AddBookmarkSkillLocalUseCase,
    private val getAllBookmarkMonstersLocalUseCase: GetAllBookmarkMonstersLocalUseCase,
    private val addBookmarkMonsterLocalUseCase: AddBookmarkMonsterLocalUseCase

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
    private var _selectedItemPosition = MutableStateFlow<Int?>(null)
    val selectedItemPosition = _selectedItemPosition.asStateFlow()
    fun setSelectedBookmarkItem(position: Int, item: ItemDto?){
        viewModelScope.launch {
            _selectedBookmarkItem.emit(item)
            _selectedItemPosition.emit(position)
        }
    }

    //전체 아이템 조회
    private var itemListAdapterListProvider: (() -> List<ItemDto>)? = null
    fun getItemListAdapterList(): List<ItemDto> {
        return itemListAdapterListProvider?.invoke() ?: emptyList()
    }
    fun getAllBookmarkItemsLocal(
        adapterListProvider: () -> List<ItemDto>
    ){
        itemListAdapterListProvider = adapterListProvider
        viewModelScope.launch {
            _currentBookmarkItemList.emit(getAllBookmarkItemsLocalUseCase.invoke())
            _currentBookmarkSkillList.emit(listOf())
            _currentBookmarkMonsterList.emit(listOf())
        }
    }

    //새로운 아이템 리스트
    private var _newItemList = MutableStateFlow<List<ItemDto>>(listOf())

    val newItemList = _newItemList.asStateFlow()

    fun updateItemListAdapter(list: List<ItemDto>){
        viewModelScope.launch {
            _newItemList.emit(list)
        }
    }

    ///////////////////////////////////////////////// 스킬 /////////////////////////////////////////////////////

    //현재 아이템 북마크 리스트
    private var _currentBookmarkSkillList = MutableSharedFlow<List<SkillDto>>()

    val currentBookmarkSkillList: SharedFlow<List<SkillDto>>
        get() = _currentBookmarkSkillList.asSharedFlow()

    //현재 선택된 스킬
    private var _selectedBookmarkSkill = MutableStateFlow<SkillDto?>(null)
    val selectedBookmarkSkill = _selectedBookmarkSkill.asStateFlow()
    private var _selectedSkillPosition = MutableStateFlow<Int?>(null)
    val selectedSkillPosition = _selectedSkillPosition.asStateFlow()
    fun setSelectedBookmarkSkill(position: Int, skill: SkillDto?){
        viewModelScope.launch {
            _selectedBookmarkSkill.emit(skill)
            _selectedSkillPosition.emit(position)
        }
    }

    //전체 스킬 조회
    private var skillListAdapterListProvider: (() -> List<SkillDto>)? = null
    fun getSkillListAdapterList(): List<SkillDto> {
        return skillListAdapterListProvider?.invoke() ?: emptyList()
    }
    fun getAllBookmarkSkillsLocal(
        adapterListProvider: () -> List<SkillDto>
    ){
        skillListAdapterListProvider = adapterListProvider
        viewModelScope.launch {
            _currentBookmarkSkillList.emit(getAllBookmarkSkillsLocalUseCase.invoke())
            _currentBookmarkItemList.emit(listOf())
            _currentBookmarkMonsterList.emit(listOf())
        }
    }

    // 새로운 스킬 리스트
    private var _newSkillList = MutableStateFlow<List<SkillDto>>(listOf())

    val newSkillList = _newSkillList.asStateFlow()

    fun updateSkillListAdapter(list: List<SkillDto>){
        viewModelScope.launch {
            _newSkillList.emit(list)
        }
    }

    ///////////////////////////////////////////////// 몬스터 /////////////////////////////////////////////////////

    //현재 몬스터 북마크 리스트
    private var _currentBookmarkMonsterList = MutableSharedFlow<List<MonsterDto>>()

    val currentBookmarkMonsterList: SharedFlow<List<MonsterDto>>
        get() = _currentBookmarkMonsterList.asSharedFlow()

    //현재 선택된 몬스터
    private var _selectedBookmarkMonster = MutableStateFlow<MonsterDto?>(null)
    val selectedBookmarkMonster = _selectedBookmarkMonster.asStateFlow()
    private var _selectedMonsterPosition = MutableStateFlow<Int?>(null)
    val selectedMonsterPosition = _selectedMonsterPosition.asStateFlow()
    fun setSelectedBookmarkMonster(position: Int, monster: MonsterDto?){
        viewModelScope.launch {
            _selectedBookmarkMonster.emit(monster)
            _selectedMonsterPosition.emit(position)
        }
    }

    //전체 몬스터 조회
    private var monsterListAdapterListProvider: (() -> List<MonsterDto>)? = null
    fun getMonsterListAdapterList(): List<MonsterDto> {
        return monsterListAdapterListProvider?.invoke() ?: emptyList()
    }
    fun getAllBookmarkMonstersLocal(
        adapterListProvider: () -> List<MonsterDto>
    ){
        monsterListAdapterListProvider = adapterListProvider
        viewModelScope.launch {
            _currentBookmarkMonsterList.emit(getAllBookmarkMonstersLocalUseCase.invoke())
            _currentBookmarkItemList.emit(listOf())
            _currentBookmarkSkillList.emit(listOf())
        }
    }

    // 새로운 몬스터 리스트
    private var _newMonsterList = MutableStateFlow<List<MonsterDto>>(listOf())

    val newMonsterList = _newMonsterList.asStateFlow()

    fun updateMonsterListAdapter(list: List<MonsterDto>){
        viewModelScope.launch {
            _newMonsterList.emit(list)
        }
    }

}
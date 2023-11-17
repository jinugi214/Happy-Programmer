package com.gumigames.presentation.ui.dogam

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.model.common.SkillDto
import com.gumigames.domain.usecase.dogam.litem.GetAllItemsLocalUseCase
import com.gumigames.domain.usecase.dogam.litem.GetAllItemsUseCase
import com.gumigames.domain.usecase.dogam.litem.SearchItemsUseCase
import com.gumigames.domain.usecase.dogam.litem.SearchItemsLocalUseCase
import com.gumigames.domain.usecase.dogam.monster.GetAllMonstersLocalUseCase
import com.gumigames.domain.usecase.dogam.monster.GetAllMonstersUseCase
import com.gumigames.domain.usecase.dogam.monster.SearchMonstersUseCase
import com.gumigames.domain.usecase.dogam.monster.SearchMonstersLocalUseCase
import com.gumigames.domain.usecase.dogam.skill.GetAllSkillsLocalUseCase
import com.gumigames.domain.usecase.dogam.skill.GetAllSkillsUseCase
import com.gumigames.domain.usecase.dogam.skill.SearchSkillsUseCase
import com.gumigames.domain.usecase.dogam.skill.SearchSkillsLocalUseCase
import com.gumigames.presentation.base.BaseViewModel
import com.gumigames.presentation.ui.common.item.ItemListApdapter
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
class DogamViewModel @Inject constructor(
    private val getAllItemsUseCase: GetAllItemsUseCase,
    private val searchItemsUseCase: SearchItemsUseCase,
    private val getAllSkillsUseCase: GetAllSkillsUseCase,
    private val searchSkillsUseCase: SearchSkillsUseCase,
    private val getAllMonstersUseCase: GetAllMonstersUseCase,
    private val searchMonstersUseCase: SearchMonstersUseCase,
    private val getAllItemsLocalUseCase: GetAllItemsLocalUseCase,
    private val getAllSkillsLocalUseCase: GetAllSkillsLocalUseCase,
    private val getAllMonstersLocalUseCase: GetAllMonstersLocalUseCase,
    private val searchItemsLocalUseCase: SearchItemsLocalUseCase,
    private val searchSkillsLocalUseCase: SearchSkillsLocalUseCase,
    private val searchMonstersLocalUseCase: SearchMonstersLocalUseCase
): BaseViewModel() {


    ////////////////////////////////////// 공통 ////////////////////////////////////////////////

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

    //검색 키워드
    private var _searchKeyword: String = ""
    fun setSearchKeyword(keyword: String){
        _searchKeyword = keyword
    }

    fun searchKeyword(){
        when(_currentTab){
            "item" -> {getSearchItems()}
            "skill" -> {getSearchSkills()}
            "monster" -> {getSearchMonsters()}
        }
    }

    ////////////////////////////////////////////// 아이템 ///////////////////////////////////////////////////////

    //현재 아이템 리스트
    private var _currentItemList = MutableStateFlow<List<ItemDto>>(listOf())

    val currentItemList = _currentItemList.asStateFlow()

    //현재 선택된 아이템
    private var _selectedItem = MutableStateFlow<ItemDto?>(null)
    val selectedItem = _selectedItem.asStateFlow()
    private var _selectedItemPosition = MutableStateFlow<Int?>(null)
    val selectedItemPosition = _selectedItemPosition.asStateFlow()
    fun setSelectedItem(position: Int, item: ItemDto?){
        viewModelScope.launch {
            _selectedItem.emit(item)
            _selectedItemPosition.emit(position)
        }
    }

    //전체 아이템 조회
    private var itemListAdapterListProvider: (() -> List<ItemDto>)? = null
    fun getItemListAdapterList(): List<ItemDto> {
        return itemListAdapterListProvider?.invoke() ?: emptyList()
    }
    fun getAllItems(
        adapterListProvider: () -> List<ItemDto>
    ){
        itemListAdapterListProvider = adapterListProvider
        viewModelScope.launch {
            _currentItemList.emit(getAllItemsLocalUseCase.invoke())
            _currentSkillList.emit(listOf())
            _currentMonsterList.emit(listOf())
        }
    }
    //아이템 검색
    fun getSearchItems(){
        viewModelScope.launch {
            if(_searchKeyword==""){ //아무것도 입력 안하면 전체 아이템 조회
                _currentItemList.emit(getAllItemsLocalUseCase.invoke())
            }else{
                _currentItemList.emit(searchItemsLocalUseCase.invoke(_searchKeyword))
            }
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

    ///////////////////////////////////////////// 스킬 ////////////////////////////////////////////////////

    //현재 스킬 리스트
    private var _currentSkillList = MutableSharedFlow<List<SkillDto>>()

    val currentSkillList: SharedFlow<List<SkillDto>>
        get() = _currentSkillList.asSharedFlow()

    //현재 선택된 스킬
    private var _selectedSkill = MutableStateFlow<SkillDto?>(null)
    val selectedSkill = _selectedSkill.asStateFlow()
    private var _selectedSkillPosition = MutableStateFlow<Int?>(null)
    val selectedSkillPosition = _selectedSkillPosition.asStateFlow()
    fun setSelectedSkill(position: Int, skill: SkillDto?){
        viewModelScope.launch {
            _selectedSkill.emit(skill)
            _selectedSkillPosition.emit(position)
        }
    }

    //전체 스킬 조회
    private var skillListAdapterListProvider: (() -> List<SkillDto>)? = null
    fun getSkillListAdapterList(): List<SkillDto> {
        return skillListAdapterListProvider?.invoke() ?: emptyList()
    }
    fun getAllSkills(
        adapterListProvider: () -> List<SkillDto>
    ){
        skillListAdapterListProvider = adapterListProvider
        viewModelScope.launch {
        _currentSkillList.emit(getAllSkillsLocalUseCase.invoke())
        _currentItemList.emit(listOf())
        _currentMonsterList.emit(listOf())
        }
    }
    //스킬 검색
    fun getSearchSkills(){
        viewModelScope.launch {
            viewModelScope.launch {
                if(_searchKeyword==""){ //아무것도 입력 안하면 전체 아이템 조회
                    _currentSkillList.emit(getAllSkillsLocalUseCase.invoke())
                }else{
                    _currentSkillList.emit(searchSkillsLocalUseCase.invoke(_searchKeyword))
                }
            }
        }
    }

    //새로운 스킬 리스트
    private var _newSkillList = MutableStateFlow<List<SkillDto>>(listOf())
    val newSkillList = _newSkillList.asStateFlow()
    fun updateSkillListAdapter(list: List<SkillDto>){
        viewModelScope.launch {
            Log.d(TAG, "updateSkillListAdapter update $list")
            _newSkillList.emit(list)
        }
    }

    ///////////////////////////////////////////// 몬스터 //////////////////////////////////////////////////

    //현재 몬스터 리스트
    private var _currentMonsterList = MutableSharedFlow<List<MonsterDto>>()

    val currentMonsterList: SharedFlow<List<MonsterDto>>
        get() = _currentMonsterList.asSharedFlow()

    //현재 선택된 몬스터
    private var _selectedMonster = MutableStateFlow<MonsterDto?>(null)
    val selectedMonster = _selectedMonster.asStateFlow()
    private var _selectedMonsterPosition = MutableStateFlow<Int?>(null)
    val selectedMonsterPosition = _selectedMonsterPosition.asStateFlow()
    fun setSelectedMonster(position: Int, monster: MonsterDto?){
        viewModelScope.launch {
            _selectedMonster.emit(monster)
            _selectedMonsterPosition.emit(position)
        }
    }

    //전체 몬스터 조회
    private var monsterListAdapterListProvider: (() -> List<MonsterDto>)? = null
    fun getMonsterListAdapterList(): List<MonsterDto> {
        return monsterListAdapterListProvider?.invoke() ?: emptyList()
    }
    fun getAllMonsters(
        adapterListProvider: () -> List<MonsterDto>
    ){
        monsterListAdapterListProvider = adapterListProvider
        viewModelScope.launch {
            _currentMonsterList.emit(getAllMonstersLocalUseCase.invoke())
            _currentItemList.emit(listOf())
            _currentSkillList.emit(listOf())
        }
    }
    //몬스터 검색
    fun getSearchMonsters(){
        viewModelScope.launch {
            if(_searchKeyword==""){ //아무것도 입력 안하면 전체 아이템 조회
                _currentMonsterList.emit(getAllMonstersLocalUseCase.invoke())
            }else{
                _currentMonsterList.emit(searchMonstersLocalUseCase.invoke(_searchKeyword))
            }
        }
    }

    //새로운 몬스터 리스트
    private var _newMonsterList = MutableStateFlow<List<MonsterDto>>(listOf())
    val newMonsterList = _newMonsterList.asStateFlow()
    fun updateMonsterListAdapter(list: List<MonsterDto>){
        viewModelScope.launch {
            _newMonsterList.emit(list)
        }
    }


}
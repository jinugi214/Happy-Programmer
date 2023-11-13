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
    fun getAllItems(){
        //TODO 원래라면 Repository를 통해 전체 아이템 조회
        viewModelScope.launch {
//            getApiResult( block = {getAllItemsUseCase.invoke()}){
//                _currentItemList.emit(it)
//            }
            _currentItemList.emit(getAllItemsLocalUseCase.invoke())
            _currentSkillList.emit(listOf())
            _currentMonsterList.emit(listOf())
        }
    }
    //아이템 검색
    fun getSearchItems(){
        viewModelScope.launch {
            if(_searchKeyword==""){ //아무것도 입력 안하면 전체 아이템 조회
//                getApiResult(block = {getAllItemsUseCase.invoke()}){
//                    _currentItemList.emit(it)
//                }
                _currentItemList.emit(getAllItemsLocalUseCase.invoke())
            }else{
//                getApiResult(block = {searchItemsUseCase.invoke(_searchKeyword)}){
//                    _currentItemList.emit(it)
//                }
                _currentItemList.emit(searchItemsLocalUseCase.invoke(_searchKeyword))
            }
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
    fun setSelectedSkill(skill: SkillDto?){
        viewModelScope.launch {
            _selectedSkill.emit(skill)
        }
    }

    //전체 스킬 조회
    fun getAllSkills(){
        viewModelScope.launch {
//            getApiResult(block = {getAllSkillsUseCase.invoke()}){
//                _currentSkillList.emit(it)
//            }
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
//                    getApiResult(block = {getAllSkillsUseCase.invoke()}){
//                        _currentSkillList.emit(it)
//                    }
                    _currentSkillList.emit(getAllSkillsLocalUseCase.invoke())
                }else{
//                    getApiResult(block = {searchSkillsUseCase.invoke(_searchKeyword)}){
//                        _currentSkillList.emit(it)
//                    }
                    _currentSkillList.emit(searchSkillsLocalUseCase.invoke(_searchKeyword))
                }
            }
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
    fun setSelectedMonster(monster: MonsterDto?){
        viewModelScope.launch {
            _selectedMonster.emit(monster)
        }
    }

    //전체 몬스터 조회
    fun getAllMonsters(){
        viewModelScope.launch {
//            getApiResult(block = {getAllMonstersUseCase.invoke()}){
//                _currentMonsterList.emit(it)
//            }
            _currentMonsterList.emit(getAllMonstersLocalUseCase.invoke())
            _currentItemList.emit(listOf())
            _currentSkillList.emit(listOf())
        }
    }
    //몬스터 검색
    fun getSearchMonsters(){
        viewModelScope.launch {
            if(_searchKeyword==""){ //아무것도 입력 안하면 전체 아이템 조회
//                getApiResult(block = {getAllMonstersUseCase.invoke()}){
//                    _currentMonsterList.emit(it)
//                }
                _currentMonsterList.emit(getAllMonstersLocalUseCase.invoke())
            }else{
//                getApiResult(block = {searchMonstersUseCase.invoke(_searchKeyword)}){
//                    _currentMonsterList.emit(it)
//                }
                _currentMonsterList.emit(searchMonstersLocalUseCase.invoke(_searchKeyword))
            }
        }
    }




}
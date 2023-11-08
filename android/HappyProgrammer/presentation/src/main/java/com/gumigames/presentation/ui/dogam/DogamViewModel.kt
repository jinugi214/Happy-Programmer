package com.gumigames.presentation.ui.dogam

import androidx.lifecycle.viewModelScope
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.model.common.SkillDto
import com.gumigames.domain.usecase.dogam.litem.AddBookmarkItemLocalUseCase
import com.gumigames.domain.usecase.dogam.monster.AddBookmarkMonsterLocalUseCase
import com.gumigames.domain.usecase.dogam.skill.AddBookmarkSkillLocalUseCase
import com.gumigames.domain.usecase.dogam.litem.GetAllItemsUseCase
import com.gumigames.domain.usecase.dogam.litem.GetSearchItemsUseCase
import com.gumigames.domain.usecase.dogam.monster.GetAllMonstersUseCase
import com.gumigames.domain.usecase.dogam.monster.GetSearchMonstersUseCase
import com.gumigames.domain.usecase.dogam.skill.GetAllSkillsUseCase
import com.gumigames.domain.usecase.dogam.skill.GetSearchSkillsUseCase
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
class DogamViewModel @Inject constructor(
    private val getAllItemsUseCase: GetAllItemsUseCase,
    private val getSearchItemsUseCase: GetSearchItemsUseCase,
    private val addBookmarkItemUseCase: AddBookmarkItemLocalUseCase,
    private val getAllSkillsUseCase: GetAllSkillsUseCase,
    private val getSearchSkillsUseCase: GetSearchSkillsUseCase,
    private val addBookmarkSkillLocalUseCase: AddBookmarkSkillLocalUseCase,
    private val getAllMonstersUseCase: GetAllMonstersUseCase,
    private val getSearchMonstersUseCase: GetSearchMonstersUseCase,
    private val addBookmarkMonsterLocalUseCase: AddBookmarkMonsterLocalUseCase
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
    private var _currentItemList = MutableSharedFlow<List<ItemDto>>()

    val currentItemList: SharedFlow<List<ItemDto>>
        get() = _currentItemList.asSharedFlow()

    //현재 선택된 아이템
    private var _selectedItem = MutableStateFlow<ItemDto?>(null)
    val selectedItem = _selectedItem.asStateFlow()
    fun setSelectedItem(item: ItemDto?){
        viewModelScope.launch {
            _selectedItem.emit(item)
        }
    }

    //전체 아이템 조회
    fun getAllItems(){
        //TODO 원래라면 Repository를 통해 전체 아이템 조회
        viewModelScope.launch {
            getApiResult( block = {getAllItemsUseCase.invoke()}){
                _currentItemList.emit(it)
            }
        }
    }
    //아이템 검색
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

    //로컬에 북마크 아이템 추가
//    fun addBookmarkItemLocal(item: ItemDto){
//        viewModelScope.launch {
//            addBookmarkItemUseCase.invoke(item)
//        }
//    }

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
            getApiResult(block = {getAllSkillsUseCase.invoke()}){
                _currentSkillList.emit(it)
            }
        }
    }
    //스킬 검색
    fun getSearchSkills(){
        viewModelScope.launch {
            viewModelScope.launch {
                if(_searchKeyword==""){ //아무것도 입력 안하면 전체 아이템 조회
                    getApiResult(block = {getAllSkillsUseCase.invoke()}){
                        _currentSkillList.emit(it)
                    }
                }else{
                    getApiResult(block = {getSearchSkillsUseCase.invoke(_searchKeyword)}){
                        _currentSkillList.emit(it)
                    }
                }
            }
        }
    }

    //로컬에 북마크 스킬 추가
//    fun addBookmarkSkillLocal(skill: SkillDto){
//        viewModelScope.launch {
//            addBookmarkSkillLocalUseCase.invoke(skill)
//        }
//    }


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
            getApiResult(block = {getAllMonstersUseCase.invoke()}){
                _currentMonsterList.emit(it)
            }
        }
    }
    //몬스터 검색
    fun getSearchMonsters(){
        viewModelScope.launch {
            if(_searchKeyword==""){ //아무것도 입력 안하면 전체 아이템 조회
                getApiResult(block = {getAllMonstersUseCase.invoke()}){
                    _currentMonsterList.emit(it)
                }
            }else{
                getApiResult(block = {getSearchMonstersUseCase.invoke(_searchKeyword)}){
                    _currentMonsterList.emit(it)
                }
            }
        }
    }

    //로컬에 북마크 몬스터 추가
//    fun addBookmarkMonsterLocal(monster: MonsterDto){
//        viewModelScope.launch {
//            addBookmarkMonsterLocalUseCase.invoke(monster)
//        }
//    }



}
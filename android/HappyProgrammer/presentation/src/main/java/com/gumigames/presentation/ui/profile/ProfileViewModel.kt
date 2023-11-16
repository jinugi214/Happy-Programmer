package com.gumigames.presentation.ui.profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.model.common.SkillDto
import com.gumigames.domain.model.user.UserInfoDto
import com.gumigames.domain.usecase.dogam.litem.GetMyItemsLocalUseCase
import com.gumigames.domain.usecase.user.GetUserInfoLocalUseCase
import com.gumigames.domain.usecase.user.GetUserInfoUseCase
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
class ProfileViewModel @Inject constructor(
    private val getMyItemsLocalUseCase: GetMyItemsLocalUseCase,
    private val getUserInfoLocalUseCase: GetUserInfoLocalUseCase
): BaseViewModel() {

    ////////////////////////////////////////// 유저 //////////////////////////////////////////////
    private var _userInfo = MutableSharedFlow<UserInfoDto>()
    val userInfo = _userInfo.asSharedFlow()
    fun getUserInfo(){
        viewModelScope.launch {
            getApiResult(block = {getUserInfoLocalUseCase.invoke()}){
                Log.d(TAG, "getUserInfoLocal 결과 : $it")
                _userInfo.emit(it)
            }
        }
    }


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
    private var _currentMyItemList = MutableSharedFlow<List<ItemDto>>()
    val currentMyItemList = _currentMyItemList.asSharedFlow()

    //현재 선택된 아이템
    private var _selectedMyItem = MutableStateFlow<ItemDto?>(null)
    val selectedMyItem = _selectedMyItem.asStateFlow()
    private var _selectedMyItemPosition = MutableStateFlow<Int?>(null)
    val selectedMyItemPosition = _selectedMyItemPosition.asStateFlow()
    fun setSelectedMyItem(position: Int, item: ItemDto?){
        viewModelScope.launch {
            _selectedMyItem.emit(item)
            _selectedMyItemPosition.emit(position)
        }
    }

    //전체 아이템 조회
    private var itemListAdapterListProvider: (() -> List<ItemDto>)? = null
    fun getItemListAdapterList(): List<ItemDto> {
        return itemListAdapterListProvider?.invoke() ?: emptyList()
    }
    fun getAllMyItemsLocal(
        adapterListProvider: () -> List<ItemDto>
    ){
        itemListAdapterListProvider = adapterListProvider
        viewModelScope.launch {
            Log.d(TAG, "getAllMyItemsLocal 결과 ${getMyItemsLocalUseCase.invoke()}")
            _currentMyItemList.emit(getMyItemsLocalUseCase.invoke())
            _currentMySkillList.emit(listOf())
            _currentMyMonsterList.emit(listOf())
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

    ////////////////////////////// 스킬 //////////////////////////////////////
    //현재  스킬 리스트
    private var _currentMySkillList = MutableSharedFlow<List<SkillDto>>()

    val currentMySkillList: SharedFlow<List<SkillDto>>
        get() = _currentMySkillList.asSharedFlow()

    fun getAllMySkillsLocal(){
        viewModelScope.launch {
            _currentMySkillList.emit(listOf())
            _currentMyItemList.emit(listOf())
            _currentMyMonsterList.emit(listOf())
        }
    }



    /////////////////////////////////// 몬스터 /////////////////////////////////
    //현재 몬스터 리스트
    private var _currentMyMonsterList = MutableSharedFlow<List<MonsterDto>>()

    val currentMyMonsterList: SharedFlow<List<MonsterDto>>
        get() = _currentMyMonsterList.asSharedFlow()


    fun getAllMyMonstersLocal(){
        viewModelScope.launch {
            _currentMyMonsterList.emit(listOf())
            _currentMyItemList.emit(listOf())
            _currentMySkillList.emit(listOf())
        }
    }

}
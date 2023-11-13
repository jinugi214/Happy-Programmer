package com.gumigames.presentation.ui.common.monster

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.model.common.SkillDto
import com.gumigames.domain.usecase.dogam.monster.AddBookmarkMonsterLocalUseCase
import com.gumigames.domain.usecase.dogam.monster.DeleteBookmarkMonsterLocalUseCase
import com.gumigames.domain.usecase.dogam.monster.ToggleBookmarkMonsterUseCase
import com.gumigames.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "차선호"
@HiltViewModel
class MonsterDetailDialogViewModel @Inject constructor(
    private val toggleBookmarkMonsterUseCase: ToggleBookmarkMonsterUseCase,
    private val addBookmarkMonsterLocalUseCase: AddBookmarkMonsterLocalUseCase,
    private val deleteBookmarkMonsterLocalUseCase: DeleteBookmarkMonsterLocalUseCase
): BaseViewModel() {

    private var _currentMonster: MonsterDto? = null
    fun setCurrentMonster(monsterDto: MonsterDto){
        _currentMonster = monsterDto
    }

    fun getCurrentMonster(): MonsterDto {
        return _currentMonster ?: MonsterDto(-1,"",0,"",null,false)
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
            Log.d(TAG, "toggleIsBookmarked item id : ${getCurrentMonster().id} ")
            getApiResult(block = {toggleBookmarkMonsterUseCase.invoke(getCurrentMonster().id)}){
                _currentIsBookmarked.emit(it)
                if(it){ //로컬에 isBookmarked true로 갱신
                    addBookmarkMonsterLocalUseCase.invoke(getCurrentMonster().id)
                }else{ //로컬에 isBookmarked false로 갱신
                    deleteBookmarkMonsterLocalUseCase.invoke(getCurrentMonster().id)
                }
            }

        }
    }
}
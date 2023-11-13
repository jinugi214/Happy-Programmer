package com.gumigames.presentation.ui.common.skill

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.model.common.SkillDto
import com.gumigames.domain.usecase.dogam.skill.AddBookmarkSkillLocalUseCase
import com.gumigames.domain.usecase.dogam.skill.DeleteBookmarkSkillLocalUseCase
import com.gumigames.domain.usecase.dogam.skill.ToggleBookmarkSkillUseCase
import com.gumigames.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "차선호"
@HiltViewModel
class SkillDetailDialogViewModel @Inject constructor(
    private val toggleBookmarkSkillUseCase: ToggleBookmarkSkillUseCase,
    private val addBookmarkSkillLocalUseCase: AddBookmarkSkillLocalUseCase,
    private val deleteBookmarkSkillLocalUseCase: DeleteBookmarkSkillLocalUseCase
): BaseViewModel() {

    private var _currentSkill: SkillDto? = null
    fun setCurrentSkill(skillDto: SkillDto){
        _currentSkill = skillDto
    }

    fun getCurrentSkill(): SkillDto {
        return _currentSkill ?: SkillDto(-1,"","",null,false)
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
            Log.d(TAG, "toggleIsBookmarked item id : ${getCurrentSkill().id} ")
            getApiResult(block = {toggleBookmarkSkillUseCase.invoke(getCurrentSkill().id)}){
                _currentIsBookmarked.emit(it)
                if(it){ //로컬에 isBookmarked true로 갱신
                    addBookmarkSkillLocalUseCase.invoke(getCurrentSkill().id)
                }else{ //로컬에 isBookmarked false로 갱신
                    deleteBookmarkSkillLocalUseCase.invoke(getCurrentSkill().id)
                }
            }

        }
    }
}
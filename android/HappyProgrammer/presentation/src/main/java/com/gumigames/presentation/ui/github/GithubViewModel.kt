package com.gumigames.presentation.ui.github

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.RepoDto
import com.gumigames.domain.usecase.GithubUseCase
import com.gumigames.domain.util.NetworkThrowable
import com.gumigames.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "차선호"
@HiltViewModel
class GithubViewModel @Inject constructor(
    private val githubUseCase: GithubUseCase
): BaseViewModel() {

    private var _userId: String = ""
    fun setUserId(userId: String){
        _userId = userId
    }


    private var _repoList = MutableSharedFlow<List<RepoDto>>()
    val repoList: SharedFlow<List<RepoDto>>
        get() = _repoList.asSharedFlow()

    fun getUserRepos(){
        getApiResult( block = {githubUseCase.invoke(_userId)}){
            _repoList.emit(it)
        }
    }

}
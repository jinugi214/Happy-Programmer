package com.gumigames.presentation.ui.bookmark

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.gumigames.domain.usecase.bookmark.item.GetAllBookmarkItemsLocalUseCase
import com.gumigames.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "차선호"
@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getAllBookmarkItemsLocalUseCase: GetAllBookmarkItemsLocalUseCase
): BaseViewModel(){

    fun getAllBookmarkItemsLocal(){
        viewModelScope.launch {
            Log.d(TAG, "getAllBookmarkItemsLocal result : ${getAllBookmarkItemsLocalUseCase.invoke()}")
        }
    }
}
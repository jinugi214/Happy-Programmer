package com.freeproject.happyprogrammers

import androidx.lifecycle.ViewModel
import com.freeproject.happyprogrammers.data.datasource.PreferenceDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource
): ViewModel() {

    fun isLogined(): Boolean{
        return preferenceDataSource.getIsLogined()
    }
}
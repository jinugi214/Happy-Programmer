package com.gumigames.data.repository

import com.gumigames.data.datasource.local.PreferenceDataSource
import com.gumigames.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val preferenceDataSource: PreferenceDataSource
): LoginRepository {
    override fun getIsLogined(): Boolean {
        return preferenceDataSource.getIsLogined()
    }

}
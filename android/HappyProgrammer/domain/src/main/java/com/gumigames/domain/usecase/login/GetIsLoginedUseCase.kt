package com.gumigames.domain.usecase.login

import com.gumigames.domain.repository.LoginRepository
import javax.inject.Inject

class GetIsLoginedUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    operator fun invoke(): Boolean{
        return loginRepository.getIsLogined()
    }
}
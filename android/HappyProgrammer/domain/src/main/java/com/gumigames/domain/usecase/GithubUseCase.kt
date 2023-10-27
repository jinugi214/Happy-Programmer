package com.gumigames.domain.usecase

import com.example.domain.model.RepoDto
import com.gumigames.domain.repository.GithubRepository
import javax.inject.Inject

class GithubUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
//    suspend operator fun invoke(user: String): List<RepoDto>{
//        return try {
//            githubRepository.getUserRepos(user)
//        } catch (e: NetworkThrowable.NetworkErrorThrowable) {
//            // 네트워크 에러 처리 코드를 여기에 추가하세요.
//            // 예: 로깅 또는 사용자에게 알림을 표시
//            emptyList() // 또는 다른 적절한 대체값을 반환할 수 있습니다.
//        }
//    }
}
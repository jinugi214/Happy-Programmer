package com.gumigames.happyprogrammer

import com.gumigames.data.datasource.dao.ItemBookmarkDao
import com.gumigames.data.datasource.sharedpreference.PreferenceDataSource
import com.gumigames.data.repository.GithubRepositoryImpl
import com.gumigames.data.repository.DogamRepositoryImpl
import com.gumigames.data.repository.LoginRepositoryImpl
import com.gumigames.data.service.GithubService
import com.gumigames.data.service.ItemService
import com.gumigames.data.service.MonsterService
import com.gumigames.domain.repository.GithubRepository
import com.gumigames.domain.repository.DogamRepository
import com.gumigames.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGithubRepository(githubService: GithubService): GithubRepository {
        return GithubRepositoryImpl(githubService)
    }

    @Singleton
    @Provides
    fun provideLoginRepository(preferenceDataSource: PreferenceDataSource): LoginRepository {
        return LoginRepositoryImpl(preferenceDataSource)
    }

    @Singleton
    @Provides
    fun providedogamRepository(itemBookmarkDao: ItemBookmarkDao, itemService: ItemService, monsterService: MonsterService): DogamRepository {
        return DogamRepositoryImpl(itemBookmarkDao = itemBookmarkDao, itemService = itemService, monsterService = monsterService)
    }

}
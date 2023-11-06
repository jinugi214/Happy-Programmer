package com.gumigames.happyprogrammer

import com.gumigames.data.datasource.dao.ItemBookmarkDao
import com.gumigames.data.datasource.dao.MonsterBookmarkDao
import com.gumigames.data.datasource.dao.SkillBookmarkDao
import com.gumigames.data.datasource.sharedpreference.PreferenceDataSource
import com.gumigames.data.repository.BookmarkRepositoryImpl
import com.gumigames.data.repository.DogamRepositoryImpl
import com.gumigames.data.repository.LoginRepositoryImpl
import com.gumigames.data.repository.MissionRepositoryImpl
import com.gumigames.data.repository.PreferenceRepositoryImpl
import com.gumigames.data.service.ItemService
import com.gumigames.data.service.MissionService
import com.gumigames.data.service.MonsterService
import com.gumigames.data.service.SkillService
import com.gumigames.domain.repository.BookmarkRepository
import com.gumigames.domain.repository.DogamRepository
import com.gumigames.domain.repository.LoginRepository
import com.gumigames.domain.repository.MissionRepository
import com.gumigames.domain.repository.PreferenceRepository
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
    fun providePreferenceRepository(preferenceDataSource: PreferenceDataSource): PreferenceRepository{
        return PreferenceRepositoryImpl(preferenceDataSource)
    }

    @Singleton
    @Provides
    fun provideLoginRepository(preferenceDataSource: PreferenceDataSource): LoginRepository {
        return LoginRepositoryImpl(preferenceDataSource)
    }

    @Singleton
    @Provides
    fun provideDogamRepository(
        itemService: ItemService,
        skillService: SkillService,
        monsterService: MonsterService,
    ): DogamRepository {
        return DogamRepositoryImpl(
            itemService = itemService,
            skillService = skillService,
            monsterService = monsterService
        )
    }

    @Singleton
    @Provides
    fun provideBookmarkRepository(
        itemBookmarkDao: ItemBookmarkDao,
        skillBookmarkDao: SkillBookmarkDao,
        monsterBookmarkDao: MonsterBookmarkDao
    ): BookmarkRepository{
        return BookmarkRepositoryImpl(
            itemBookmarkDao = itemBookmarkDao,
            skillBookmarkDao = skillBookmarkDao,
            monsterBookmarkDao = monsterBookmarkDao
        )
    }

    @Singleton
    @Provides
    fun provideMissionRepository(missionService: MissionService): MissionRepository{
        return MissionRepositoryImpl(missionService)
    }

}
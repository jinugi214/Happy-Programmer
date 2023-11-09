package com.gumigames.happyprogrammer

import com.gumigames.data.datasource.dao.ItemDao
import com.gumigames.data.datasource.dao.MonsterDao
import com.gumigames.data.datasource.dao.SkillDao
import com.gumigames.data.datasource.sharedpreference.PreferenceDataSource
import com.gumigames.data.repository.DogamRepositoryImpl
import com.gumigames.data.repository.UserRepositoryImpl
import com.gumigames.data.repository.MissionRepositoryImpl
import com.gumigames.data.repository.PreferenceRepositoryImpl
import com.gumigames.data.service.ItemService
import com.gumigames.data.service.MissionService
import com.gumigames.data.service.MonsterService
import com.gumigames.data.service.SkillService
import com.gumigames.data.service.UserService
import com.gumigames.domain.repository.DogamRepository
import com.gumigames.domain.repository.UserRepository
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
    fun provideLoginRepository(
        preferenceDataSource: PreferenceDataSource,
        userService: UserService
    ): UserRepository {
        return UserRepositoryImpl(
            preferenceDataSource = preferenceDataSource,
            userService = userService
        )
    }

    @Singleton
    @Provides
    fun provideDogamRepository(
        itemService: ItemService,
        skillService: SkillService,
        monsterService: MonsterService,
        itemDao: ItemDao,
        skillDao: SkillDao,
        monsterDao: MonsterDao,
        preferenceDataSource: PreferenceDataSource
    ): DogamRepository {
        return DogamRepositoryImpl(
            itemService = itemService,
            skillService = skillService,
            monsterService = monsterService,
            itemDao = itemDao,
            skillDao = skillDao,
            monsterDao = monsterDao,
            preferenceDataSource = preferenceDataSource
        )
    }


    @Singleton
    @Provides
    fun provideMissionRepository(missionService: MissionService): MissionRepository{
        return MissionRepositoryImpl(missionService)
    }

}
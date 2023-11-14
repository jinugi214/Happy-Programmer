package com.gumigames.happyprogrammer

import android.content.Context
import androidx.room.Room
import com.gumigames.data.datasource.dao.ItemDao
import com.gumigames.data.datasource.dao.MonsterDao
import com.gumigames.data.datasource.dao.SkillDao
import com.gumigames.data.datasource.dao.UserDao
import com.gumigames.data.datasource.db.DogamDatabase
import com.gumigames.data.datasource.sharedpreference.PreferenceDataSource
import com.gumigames.data.datasource.sharedpreference.PreferenceDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun providePreferenceDatasource(
        @ApplicationContext context: Context
    ) : PreferenceDataSource = PreferenceDataSourceImpl(context)

    @Singleton
    @Provides
    fun provideBookmarkDatabase(
        @ApplicationContext context: Context
    ): DogamDatabase = Room
        .databaseBuilder(context, DogamDatabase::class.java, "bookmarks.db")
        .build()
    @Singleton
    @Provides
    fun provideItemDao(dogamDatabase: DogamDatabase): ItemDao = dogamDatabase.itemDao()

    @Singleton
    @Provides
    fun provideSkillDao(dogamDatabase: DogamDatabase): SkillDao = dogamDatabase.skillDao()

    @Singleton
    @Provides
    fun provideMonsterDao(dogamDatabase: DogamDatabase): MonsterDao = dogamDatabase.monsterDao()

    @Singleton
    @Provides
    fun provideUserDao(dogamDatabase: DogamDatabase): UserDao = dogamDatabase.userDao()

}
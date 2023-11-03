package com.gumigames.happyprogrammer

import android.content.Context
import androidx.room.Room
import com.gumigames.data.datasource.dao.ItemBookmarkDao
import com.gumigames.data.datasource.dao.SkillBookmarkDao
import com.gumigames.data.datasource.db.BookmarkDatabase
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
    ): BookmarkDatabase = Room
        .databaseBuilder(context, BookmarkDatabase::class.java, "bookmarks.db")
        .build()
    @Singleton
    @Provides
    fun provideItemBookmarkDao(bookmarkDatabase: BookmarkDatabase): ItemBookmarkDao = bookmarkDatabase.itemBookmarkDao()

    @Singleton
    @Provides
    fun provideSkillBookmarkDao(bookmarkDatabase: BookmarkDatabase): SkillBookmarkDao = bookmarkDatabase.skillBookmarkDao()

}
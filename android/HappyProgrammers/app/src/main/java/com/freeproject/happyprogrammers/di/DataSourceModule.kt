package com.freeproject.happyprogrammers.di

import android.content.Context
import com.freeproject.happyprogrammers.data.datasource.PreferenceDataSource
import com.freeproject.happyprogrammers.data.datasource.PreferenceDataSourceImpl
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
    fun provideAuthDatasource(
        @ApplicationContext context: Context
    ) : PreferenceDataSource = PreferenceDataSourceImpl(context)

}
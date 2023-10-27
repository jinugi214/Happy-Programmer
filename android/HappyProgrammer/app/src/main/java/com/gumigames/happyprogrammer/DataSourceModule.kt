package com.gumigames.happyprogrammer

import android.content.Context
import com.gumigames.data.datasource.local.PreferenceDataSource
import com.gumigames.data.datasource.local.PreferenceDataSourceImpl
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

}
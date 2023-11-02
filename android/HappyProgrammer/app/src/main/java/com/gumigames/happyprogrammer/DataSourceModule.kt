package com.gumigames.happyprogrammer

import android.content.Context
import com.gumigames.data.datasource.local.PreferenceDataSource
import com.gumigames.data.datasource.local.PreferenceDataSourceImpl
import com.gumigames.data.datasource.remote.GithubDataSource
import com.gumigames.data.datasource.remote.GithubDataSourceImpl
import com.gumigames.data.service.GithubService
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
    fun provideGithubDatasource(
        githubService: GithubService
    ): GithubDataSource = GithubDataSourceImpl(githubService)

}
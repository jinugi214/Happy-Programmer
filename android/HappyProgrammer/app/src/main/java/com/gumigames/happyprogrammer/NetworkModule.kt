package com.gumigames.happyprogrammer

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.gumigames.data.datasource.sharedpreference.PreferenceDataSource
import com.gumigames.data.interceptor.AuthInterceptor
import com.gumigames.data.service.ItemService
import com.gumigames.data.service.MissionService
import com.gumigames.data.service.MonsterService
import com.gumigames.data.service.SkillService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(preferenceDataSource: PreferenceDataSource): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(5000, TimeUnit.MILLISECONDS)
        .connectTimeout(5000, TimeUnit.MILLISECONDS)
        .addInterceptor(HttpLoggingInterceptor())
        .addInterceptor(AuthInterceptor(preferenceDataSource))
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
//    }

    @Singleton
    @Provides
    fun provideRetrofit(
        preferenceDataSource: PreferenceDataSource
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create(),
            ),
        )
        .client(provideOkHttpClient(preferenceDataSource))
        .build()

    @Singleton
    @Provides
    fun provideItemService(
        retrofit: Retrofit
    ): ItemService = retrofit.create(ItemService::class.java)

    @Singleton
    @Provides
    fun provideMonsterService(
        retrofit: Retrofit
    ): MonsterService = retrofit.create(MonsterService::class.java)

    @Singleton
    @Provides
    fun provideSkillService(
        retrofit: Retrofit
    ): SkillService = retrofit.create(SkillService::class.java)

    @Singleton
    @Provides
    fun provideMissionService(
        retrofit: Retrofit
    ): MissionService = retrofit.create(MissionService::class.java)

}
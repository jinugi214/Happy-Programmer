package com.freeproject.happyprogrammers.di

import com.freeproject.happyprogrammers.BuildConfig
import com.freeproject.happyprogrammers.di.NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    private const val BASE_URL = BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(5000, TimeUnit.MILLISECONDS)
        .connectTimeout(5000, TimeUnit.MILLISECONDS)
        .addInterceptor(HttpLoggingInterceptor())
//        .addInterceptor(ResponseInterceptor(preferenceDataSource))
//        .addInterceptor(RequestInterceptor(preferenceDataSource))
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
//    }

    @Singleton
    @Provides
    fun provideRetrofit(
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create(),
            ),
        )
        .client(provideOkHttpClient())
        .build()

//    @Singleton
//    @Provides
//    fun provideBusinessService(
//        retrofit: Retrofit,
//    ): BusinessService = retrofit.create(BusinessService::class.java)

}
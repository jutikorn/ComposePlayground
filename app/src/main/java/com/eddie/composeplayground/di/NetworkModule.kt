package com.eddie.composeplayground.di

import com.eddie.composeplayground.BuildConfig
import com.eddie.composeplayground.api.PlaceHolderService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideGsonFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        converter: GsonConverterFactory,
        logging: HttpLoggingInterceptor
    ): Retrofit = Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(converter)
        .client(OkHttpClient.Builder().addInterceptor(logging).build())
        .build()

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): PlaceHolderService {
        return retrofit.create(PlaceHolderService::class.java)
    }
}
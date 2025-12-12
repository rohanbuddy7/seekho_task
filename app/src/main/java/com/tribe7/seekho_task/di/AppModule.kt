package com.tribe7.seekho_task.di

import android.content.Context
import com.tribe7.seekho_task.data.api.ApiService
import com.tribe7.seekho_task.data.local.dao.AnimeDao
import com.tribe7.seekho_task.data.local.dao.AnimeDetailDao
import com.tribe7.seekho_task.data.local.db.AppDatabase
import com.tribe7.seekho_task.data.network.NetworkModule
import com.tribe7.seekho_task.data.repo.AnimeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDao(app: AppDatabase): AnimeDao = app.animeDao()

    @Provides
    @Singleton
    fun provideAnimeDetailDao(app: AppDatabase): AnimeDetailDao = app.animeDetailDao()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getInstance(context = context)

    @Provides
    @Singleton
    fun provideApiService(): ApiService = NetworkModule.api

    @Provides
    @Singleton
    fun provideAnimeRepo(apiService: ApiService, dao: AnimeDao, animeDetailDao: AnimeDetailDao): AnimeRepo =
        AnimeRepo(apiService, dao, animeDetailDao)

}
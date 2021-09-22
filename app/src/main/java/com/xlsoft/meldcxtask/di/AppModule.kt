package com.xlsoft.meldcxtask.di

import android.content.Context
import androidx.room.Room
import com.xlsoft.meldcxtask.BuildConfig
import com.xlsoft.meldcxtask.core.data.localdb.AppDatabase
import com.xlsoft.meldcxtask.data.datasources.localdb.SearchHistoryDao
import com.xlsoft.meldcxtask.data.repository.Repository
import com.xlsoft.meldcxtask.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext app: Context
    ) : AppDatabase = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        BuildConfig.DB_NAME,
    ).build()

    @Singleton
    @Provides
    fun provideSearchHistoryDao(db : AppDatabase) = db.searchHistoryDao()

    @Provides
    @Singleton
    fun provideRepository(searchHistoryDao: SearchHistoryDao) : Repository = RepositoryImpl(searchHistoryDao)
}
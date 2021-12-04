package com.example.latestnews.di

import android.content.Context
import androidx.room.Room
import com.example.latestnews.core.db.AppDatabase
import com.example.latestnews.data.local.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "News-db"
        ).build()

    @Provides
    @Singleton
    fun provideDao(db: AppDatabase): NewsDao = db.getNewsDao()
}
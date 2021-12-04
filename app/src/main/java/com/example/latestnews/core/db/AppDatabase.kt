package com.example.latestnews.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.latestnews.data.local.NewsDao
import com.example.latestnews.data.local.NewsEntity

@Database(
    entities = [NewsEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
}
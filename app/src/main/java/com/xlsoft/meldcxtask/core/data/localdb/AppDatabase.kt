package com.xlsoft.meldcxtask.core.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xlsoft.meldcxtask.data.datasources.localdb.SearchHistoryDao
import com.xlsoft.meldcxtask.data.models.history.SearchHistory

@Database(entities = [SearchHistory::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun searchHistoryDao() : SearchHistoryDao
}
package com.xlsoft.meldcxtask.data.datasources.localdb

import androidx.room.*
import com.xlsoft.meldcxtask.data.models.history.SearchHistory
import kotlinx.coroutines.flow.Flow


@Dao
interface SearchHistoryDao {

    @Query("SELECT * FROM SearchHistory")
    fun getAllSearchHistory() : Flow<List<SearchHistory>>

    @Insert
    suspend fun insertSearchHistory(searchHistory: SearchHistory)

    @Delete
    suspend fun deleteSearchHistory(searchHistory: SearchHistory)
}
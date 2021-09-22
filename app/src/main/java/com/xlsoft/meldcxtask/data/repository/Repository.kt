package com.xlsoft.meldcxtask.data.repository

import com.xlsoft.meldcxtask.data.models.history.SearchHistory
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun insertSearchHistory(searchHistory: SearchHistory)
    suspend fun getAllHistories() : Flow<List<SearchHistory>>
    suspend fun deleteHistory(searchHistory: SearchHistory)
    suspend fun findSearchHistory(queryString: String) : Flow<List<SearchHistory>>
}
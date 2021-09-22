package com.xlsoft.meldcxtask.data.repository.home

import com.xlsoft.meldcxtask.data.datasources.localdb.SearchHistoryDao
import com.xlsoft.meldcxtask.data.models.history.SearchHistory
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(val searchHistoryDao: SearchHistoryDao)
    : Repository{

    override suspend fun insertSearchHistory(searchHistory: SearchHistory) {
        searchHistoryDao.insertSearchHistory(searchHistory)
    }

    override suspend fun getAllHistories() : Flow<List<SearchHistory>> {
        return searchHistoryDao.getAllSearchHistory()
    }

    override suspend fun deleteHistory(searchHistory: SearchHistory) {
        searchHistoryDao.deleteSearchHistory(searchHistory)
    }
}
package com.xlsoft.meldcxtask.data.repository.home

import com.xlsoft.meldcxtask.data.datasources.localdb.SearchHistoryDao
import com.xlsoft.meldcxtask.data.models.history.SearchHistory

class RepositoryImpl(val searchHistoryDao: SearchHistoryDao)
    : Repository{

    override suspend fun insertSearchHistory(searchHistory: SearchHistory) {
        searchHistoryDao.insertSearchHistory(searchHistory)
    }
}
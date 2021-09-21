package com.xlsoft.meldcxtask.data.repository.home

import com.xlsoft.meldcxtask.data.models.history.SearchHistory

interface Repository {
    suspend fun insertSearchHistory(searchHistory: SearchHistory)
}
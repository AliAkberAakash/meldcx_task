package com.xlsoft.meldcxtask.ui.features.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.xlsoft.meldcxtask.core.ui.BaseViewModel
import com.xlsoft.meldcxtask.data.models.history.SearchHistory
import com.xlsoft.meldcxtask.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: Repository)
    : BaseViewModel() {

    fun insertSearchHistory(searchHistory: SearchHistory){
        viewModelScope.launch {
            repository.insertSearchHistory(searchHistory)
            Log.d("homeViewModel", "saved in db")
        }
    }

}
package com.xlsoft.meldcxtask.ui.features.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xlsoft.meldcxtask.core.ui.BaseViewModel
import com.xlsoft.meldcxtask.data.models.history.SearchHistory
import com.xlsoft.meldcxtask.data.repository.home.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val repository: Repository)
    : BaseViewModel() {

    private var _historyList = MutableLiveData<List<SearchHistory>>()
    val historyList : LiveData<List<SearchHistory>>
        get() = _historyList

    fun getHistoryList(){
        viewModelScope.launch {
            repository.getAllHistories().collect {
                Log.d("HistoryViewModel",it.toString())
                _historyList.postValue(it)
            }
        }
    }

}
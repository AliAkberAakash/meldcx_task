package com.xlsoft.meldcxtask.ui.features.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xlsoft.meldcxtask.core.ui.BaseViewModel
import com.xlsoft.meldcxtask.data.models.history.SearchHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(): BaseViewModel() {

    private var _selectedHistory = MutableLiveData<SearchHistory>()
    val selectedHistory : LiveData<SearchHistory>
        get() = _selectedHistory

    fun setSelectedHistory(history: SearchHistory){
        _selectedHistory.postValue(history)
    }

}
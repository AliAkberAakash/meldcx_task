package com.xlsoft.meldcxtask.ui.main
import android.util.Log
import com.xlsoft.meldcxtask.core.ui.BaseViewModel
import com.xlsoft.meldcxtask.data.datasources.localdb.SearchHistoryDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel(){

    init {
        Log.d("MainViewModel", "MainViewModel is created")
       // Log.d("MainViewModel", "MainViewModel is created ${shDao.toString()}")
    }

}
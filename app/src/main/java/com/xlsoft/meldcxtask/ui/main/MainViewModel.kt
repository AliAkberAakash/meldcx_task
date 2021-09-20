package com.xlsoft.meldcxtask.ui.main
import android.util.Log
import com.xlsoft.meldcxtask.core.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel(){

    init {
        Log.d("MainViewModel", "MainViewModel is created")
    }

}
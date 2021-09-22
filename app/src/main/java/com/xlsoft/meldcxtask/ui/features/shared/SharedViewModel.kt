package com.xlsoft.meldcxtask.ui.features.shared

import android.util.Log
import com.xlsoft.meldcxtask.core.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(): BaseViewModel() {
    init {
        Log.d("SharedViewModel", "Shared ViewModel was created")
    }
}
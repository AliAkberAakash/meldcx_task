package com.xlsoft.meldcxtask.core.ui

import androidx.lifecycle.*
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()
    val loader = MutableLiveData<Boolean>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
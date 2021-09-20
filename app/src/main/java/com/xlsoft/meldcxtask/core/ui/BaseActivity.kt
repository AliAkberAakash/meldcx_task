package com.xlsoft.meldcxtask.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<ViewModel: BaseViewModel, Binding : ViewDataBinding> : AppCompatActivity() {

    lateinit var viewModel: ViewModel
    lateinit var binding: Binding

    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel = ViewModelProvider(this, factory).get(getViewModelClass())
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
        //lifecycle.addObserver(viewModel)
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }

}
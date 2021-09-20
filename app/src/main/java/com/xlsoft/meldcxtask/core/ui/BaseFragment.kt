package com.xlsoft.meldcxtask.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<ViewModel: BaseViewModel, Binding : ViewDataBinding> : Fragment() {

    lateinit var viewModel: ViewModel
    lateinit var binding: Binding

    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel = ViewModelProvider(this, factory).get(getViewModelClass())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this
        //lifecycle.addObserver(viewModel)
        return binding.root
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }

}
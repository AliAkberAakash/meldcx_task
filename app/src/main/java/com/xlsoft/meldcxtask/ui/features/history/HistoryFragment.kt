package com.xlsoft.meldcxtask.ui.features.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.xlsoft.meldcxtask.R
import com.xlsoft.meldcxtask.core.ui.BaseFragment
import com.xlsoft.meldcxtask.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    private val _viewModel : HistoryViewModel by viewModels()
    private lateinit var adapter : HistoryListAdapter

    override fun getLayoutId() = R.layout.fragment_history

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel.historyList.observe(viewLifecycleOwner){
            adapter = HistoryListAdapter(it)
            binding.historyList.adapter = adapter
        }

        _viewModel.getHistoryList()

    }

}
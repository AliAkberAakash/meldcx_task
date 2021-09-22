package com.xlsoft.meldcxtask.ui.features.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import com.xlsoft.meldcxtask.R
import com.xlsoft.meldcxtask.core.ui.BaseFragment
import com.xlsoft.meldcxtask.databinding.FragmentHistoryBinding
import com.xlsoft.meldcxtask.ui.features.shared.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    private val _viewModel : HistoryViewModel by viewModels()
    private val _sharedViewModel : SharedViewModel by navGraphViewModels(R.id.nav_graph)

    private lateinit var adapter : HistoryListAdapter

    override fun getLayoutId() = R.layout.fragment_history

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _sharedViewModel

        _viewModel.historyList.observe(viewLifecycleOwner){
            adapter = HistoryListAdapter(it){searchHistory->
                _viewModel.deleteHistory(searchHistory)
            }
            binding.historyList.adapter = adapter
        }

        _viewModel.getHistoryList()

    }

}
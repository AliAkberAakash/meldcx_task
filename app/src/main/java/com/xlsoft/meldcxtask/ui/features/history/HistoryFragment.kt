package com.xlsoft.meldcxtask.ui.features.history

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.xlsoft.meldcxtask.R
import com.xlsoft.meldcxtask.core.ui.BaseFragment
import com.xlsoft.meldcxtask.databinding.FragmentHistoryBinding
import com.xlsoft.meldcxtask.ui.features.shared.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

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
            adapter = HistoryListAdapter(
                historyList = it,
                deleteCallBack = {searchHistory->
                    val file = File(searchHistory.imagePath)
                    val deleted: Boolean = file.delete()
                    if(deleted){
                        Log.d("HistoryFragment", "Deleted image file from storage")
                        _viewModel.deleteHistory(searchHistory)
                    }else{
                        Log.d("HistoryFragment", "Failed to delete image")
                    }
                },
                itemClickCallBack = {searchHistory->
                    _sharedViewModel.setSelectedHistory(searchHistory)
                    findNavController().navigateUp()
                }
            )
            binding.historyList.adapter = adapter
        }

        _viewModel.getHistoryList()

    }

}
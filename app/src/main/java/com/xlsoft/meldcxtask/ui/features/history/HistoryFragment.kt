package com.xlsoft.meldcxtask.ui.features.history

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
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

        setListeners()
        setObservers()

        // fetch all histories
        _viewModel.getHistoryList()

    }

    /**
     * sets Listeners for the searchView
     */
    private fun setListeners(){
        binding.searchLayout.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                _viewModel.findSearchHistory("%${query}%") // search for url that contains query string
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                _viewModel.findSearchHistory("%${query}%") // search for url that contains query string
                return false
            }

        })

        // When searchView is closed, reload all the data from the database
        binding.searchLayout.setOnCloseListener {
            _viewModel.getHistoryList()
            true
        }
    }

    /**
     * Setup observers to LiveData objects
     */
    private fun setObservers(){
        _viewModel.historyList.observe(viewLifecycleOwner){

            // create adapter with searchHistory list fetched from the database
            adapter = HistoryListAdapter(
                historyList = it,
                deleteCallBack = {searchHistory->
                    val file = File(searchHistory.imagePath) // get the file from path
                    val deleted: Boolean = file.delete() // delete the file from storage
                    if(deleted){
                        Log.d("HistoryFragment", "Deleted image file from storage")
                        _viewModel.deleteHistory(searchHistory) // if file is deleted, delete the database entry too
                    }else{
                        Log.d("HistoryFragment", "Failed to delete image")
                    }
                },
                itemClickCallBack = {searchHistory->

                    // set selected searchHistory in SharedViewModel so that
                    // HomeFragments knows which url to load
                    _sharedViewModel.setSelectedHistory(searchHistory)
                    findNavController().navigateUp() //go back to HomeFragment
                }
            )
            //set the adapter to recyclerView
            binding.historyList.adapter = adapter
        }
    }

}
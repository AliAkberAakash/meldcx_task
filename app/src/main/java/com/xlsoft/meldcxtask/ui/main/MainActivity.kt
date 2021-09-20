package com.xlsoft.meldcxtask.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.xlsoft.meldcxtask.R
import com.xlsoft.meldcxtask.core.ui.BaseActivity
import com.xlsoft.meldcxtask.data.datasources.localdb.SearchHistoryDao
import com.xlsoft.meldcxtask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(){

    private val _viewModel : MainViewModel by  viewModels()

    @Inject
    lateinit var shDao : SearchHistoryDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel
        Log.d("MainActivity", "DB is created ${shDao.toString()}")
    }

    override fun getLayoutId() = R.layout.activity_main
}
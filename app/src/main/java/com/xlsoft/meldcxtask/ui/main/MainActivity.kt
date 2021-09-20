package com.xlsoft.meldcxtask.ui.main

import android.os.Bundle
import com.xlsoft.meldcxtask.R
import com.xlsoft.meldcxtask.core.ui.BaseActivity
import com.xlsoft.meldcxtask.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutId() = R.layout.activity_main
}
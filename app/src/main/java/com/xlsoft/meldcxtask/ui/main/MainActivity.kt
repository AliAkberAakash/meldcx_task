package com.xlsoft.meldcxtask.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.xlsoft.meldcxtask.R
import com.xlsoft.meldcxtask.core.ui.BaseActivity
import com.xlsoft.meldcxtask.data.datasources.localdb.SearchHistoryDao
import com.xlsoft.meldcxtask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            navController.graph,
        )

        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        )

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun getLayoutId() = R.layout.activity_main
}
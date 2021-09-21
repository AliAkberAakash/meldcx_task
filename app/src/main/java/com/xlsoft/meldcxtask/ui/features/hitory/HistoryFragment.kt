package com.xlsoft.meldcxtask.ui.features.hitory

import androidx.fragment.app.viewModels
import com.xlsoft.meldcxtask.R
import com.xlsoft.meldcxtask.core.ui.BaseFragment
import com.xlsoft.meldcxtask.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    private val _historyViewModel : HistoryViewModel by viewModels()

    override fun getLayoutId() = R.layout.fragment_history

}
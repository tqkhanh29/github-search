package com.khanhtq.githubsearch.ui.search

import androidx.fragment.app.viewModels
import com.khanhtq.common.base.BaseFragment
import com.khanhtq.githubsearch.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel>() {
    private val mainViewModel: MainViewModel by viewModels<MainViewModel> { viewModelFactory }

    override val layoutId: Int = R.layout.main_fragment

    override fun getViewModel(): MainViewModel = mainViewModel
}
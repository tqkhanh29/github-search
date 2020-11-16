package com.khanhtq.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.khanhtq.common.base.BaseFragment
import com.khanhtq.search.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override val viewModel: SearchViewModel by viewModels { viewModelFactory }
    override val layoutId: Int = R.layout.fragment_search
    override fun isReturnTransition(): Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
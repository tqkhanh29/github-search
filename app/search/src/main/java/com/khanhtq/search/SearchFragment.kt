package com.khanhtq.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.khanhtq.common.state.Status
import com.khanhtq.search.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    lateinit var userAdapter: UserAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SearchViewModel by viewModels { viewModelFactory }
    lateinit var viewDataBinding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate<FragmentSearchBinding>(
            inflater,
            R.layout.fragment_search,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        viewDataBinding.viewModel = viewModel
        viewDataBinding.executePendingBindings()
        sharedElementReturnTransition =
            TransitionInflater.from(requireContext()).inflateTransition(
                com.khanhtq.common.R.transition.move
            )
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAdapter = UserAdapter(this) { userEntity, imageView ->

        }
        viewDataBinding.contentLayout.listView.run {
            adapter = userAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        viewModel.searchResultLiveData.observe(viewLifecycleOwner) { resource ->
            if (resource.status == Status.SUCCESS) {
                userAdapter.submitList(resource.data)
            }
        }
    }

}
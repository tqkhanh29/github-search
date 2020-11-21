package com.khanhtq.githubsearch.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.khanhtq.common.state.Status
import com.khanhtq.githubsearch.R
import com.khanhtq.githubsearch.databinding.FragmentSearchBinding
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
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAdapter = UserAdapter(this) { userEntity, imageView ->
            val extras = FragmentNavigatorExtras(imageView to userEntity.userName)
            findNavController().navigate(
                SearchFragmentDirections.showProfile(
                    userEntity.userName,
                    userEntity.avatar
                ), extras
            )
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
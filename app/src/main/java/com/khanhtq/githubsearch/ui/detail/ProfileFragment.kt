package com.khanhtq.githubsearch.ui.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.khanhtq.common.state.Status
import com.khanhtq.githubsearch.R
import com.khanhtq.githubsearch.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val profileViewModel: ProfileViewModel by viewModels { viewModelFactory }
    private val params by navArgs<ProfileFragmentArgs>()
    private lateinit var viewDataBinding: FragmentProfileBinding
    private val repoAdapter = RepoAdapter()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil
                .inflate(
                        inflater,
                        R.layout.fragment_profile,
                        container,
                        false
                )
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewDataBinding.viewModel = profileViewModel
        viewDataBinding.executePendingBindings()
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.repoLayout.listView.run {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = repoAdapter
        }
        Glide.with(this).load(params.avatar).into(viewDataBinding.imgAvatar)
        profileViewModel.initUsername(params.userName)
        profileViewModel.repositoriesLiveData.observe(viewLifecycleOwner) {
            if (it.status == Status.SUCCESS) {
                repoAdapter.submitList(it.data)
            }
        }
    }
}
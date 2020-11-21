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
        profileViewModel.initUsername(params.userName)
    }
}
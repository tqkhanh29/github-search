package com.khanhtq.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionInflater
import com.khanhtq.common.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

abstract class BaseFragment<DB : ViewDataBinding, VM : ViewModel> : Fragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    protected abstract val layoutId: Int

    protected abstract val viewModel: VM

    protected lateinit var viewDataBinding: DB

    protected abstract fun isReturnTransition(): Boolean

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate<DB>(inflater, layoutId, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        if (isReturnTransition()) {
            sharedElementReturnTransition =
                TransitionInflater.from(requireContext()).inflateTransition(
                    R.transition.move
                )
        } else {
            sharedElementEnterTransition =
                TransitionInflater.from(requireContext()).inflateTransition(
                    R.transition.move
                )
        }
        return viewDataBinding.root
    }
}
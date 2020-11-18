package com.khanhtq.userdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.khanhtq.common.base.BaseFragment
import com.khanhtq.userdetail.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override val layoutId: Int = R.layout.fragment_profile
    override val viewModel: ProfileViewModel by viewModels { viewModelFactory }
    override fun isReturnTransition(): Boolean = false
}
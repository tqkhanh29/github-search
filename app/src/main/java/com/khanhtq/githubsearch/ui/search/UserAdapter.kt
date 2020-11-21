package com.khanhtq.githubsearch.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.khanhtq.common.base.DataBoundListAdapter
import com.khanhtq.core.domain.entity.UserEntity
import com.khanhtq.githubsearch.R
import com.khanhtq.githubsearch.databinding.ItemUserBinding

class UserAdapter(
    private val fragment: Fragment,
    private val itemClickCallback: (UserEntity, ImageView) -> Unit
) : DataBoundListAdapter<UserEntity, ItemUserBinding>(
    diffCallback = object : DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem.userName == newItem.userName
        }

        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem.avatar == newItem.avatar
                    && oldItem.userName == newItem.userName
        }

    }
) {
    override fun createBinding(parent: ViewGroup): ItemUserBinding {
        val binding = DataBindingUtil
            .inflate<ItemUserBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_user,
                parent,
                false
            )
        binding.root.setOnClickListener {
            binding.model?.let {
                itemClickCallback.invoke(it, binding.imgAvatar)
            }
        }
        return binding
    }

    override fun bind(binding: ItemUserBinding, item: UserEntity) {
        binding.model = item
        Glide.with(fragment).load(item.avatar).into(binding.imgAvatar)
    }
}
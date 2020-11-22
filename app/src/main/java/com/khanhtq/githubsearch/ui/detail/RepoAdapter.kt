package com.khanhtq.githubsearch.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.khanhtq.common.base.DataBoundListAdapter
import com.khanhtq.core.domain.entity.RepoEntity
import com.khanhtq.githubsearch.R
import com.khanhtq.githubsearch.databinding.ItemRepoBinding

class RepoAdapter() : DataBoundListAdapter<RepoEntity, ItemRepoBinding>(
        diffCallback = object : DiffUtil.ItemCallback<RepoEntity>() {
            override fun areItemsTheSame(oldItem: RepoEntity, newItem: RepoEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RepoEntity, newItem: RepoEntity): Boolean {
                return oldItem.name == newItem.name
                        && oldItem.desc == newItem.desc
                        && oldItem.id == newItem.id
            }

        }
) {
    override fun createBinding(parent: ViewGroup): ItemRepoBinding {
        return DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_repo,
                parent,
                false
        )
    }

    override fun bind(binding: ItemRepoBinding, item: RepoEntity) {
        binding.repo = item
    }
}
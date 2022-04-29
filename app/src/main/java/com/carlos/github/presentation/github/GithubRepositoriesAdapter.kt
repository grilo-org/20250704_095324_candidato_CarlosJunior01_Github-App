package com.carlos.github.presentation.github

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.carlos.core.domain.model.GitRepositories

class GithubRepositoriesAdapter: ListAdapter<GitRepositories, GithubRepositoriesViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepositoriesViewHolder {
        return GithubRepositoriesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: GithubRepositoriesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object: DiffUtil.ItemCallback<GitRepositories>() {
            override fun areItemsTheSame(
                oldItem: GitRepositories,
                newItem: GitRepositories
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: GitRepositories,
                newItem: GitRepositories
            ): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}
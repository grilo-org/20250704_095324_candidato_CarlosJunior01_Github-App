package com.carlos.github.presentation.github

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class GitReposLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<GitReposLoadMoreStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = GitReposLoadMoreStateViewHolder.create(parent, retry)

    override fun onBindViewHolder(
        holder: GitReposLoadMoreStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)
}
package com.carlos.github.presentation.github

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carlos.core.domain.model.GitRepositories
import com.carlos.github.R
import com.carlos.github.databinding.RecyclerItemListBinding

class GithubRepositoriesViewHolder(
    itemListBinding: RecyclerItemListBinding
): RecyclerView.ViewHolder(itemListBinding.root) {

    private val textAuthorName = itemListBinding.authorName
    private val textRepoName = itemListBinding.repoName
    private val textFork = itemListBinding.txtFork
    private val textStars = itemListBinding.txtStars
    private val userImage = itemListBinding.userImage

    fun bind(gitRepositories: GitRepositories) {
        textAuthorName.text = gitRepositories.login
        textRepoName.text = gitRepositories.name
        textFork.text = gitRepositories.forksCount.toString()
        textStars.text = gitRepositories.stargazersCount.toString()

        Glide.with(itemView)
            .load(gitRepositories.avatarUrl)
            .fallback(R.drawable.ic_launcher_foreground)
            .into(userImage)
    }

    companion object {
        fun create(parent: ViewGroup): GithubRepositoriesViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = RecyclerItemListBinding.inflate(inflater, parent, false)
            return GithubRepositoriesViewHolder(itemBinding)
        }
    }
}
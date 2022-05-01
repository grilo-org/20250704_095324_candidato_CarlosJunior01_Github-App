package com.carlos.github.framework

import androidx.paging.PagingSource
import com.carlos.core.data.repository.GitHubRemoteDataSource
import com.carlos.core.data.repository.GithubReposRepository
import com.carlos.core.domain.model.GitRepositories
import com.carlos.github.framework.network.response.GitHubReposResponseDTO
import com.carlos.github.framework.paging.GitHubPagingSource
import javax.inject.Inject

class GitHubReposRepositoryImpl @Inject constructor(
    private val remoteDataSource: GitHubRemoteDataSource<GitHubReposResponseDTO>
): GithubReposRepository {

    override fun getRepositories(query: String): PagingSource<Int, GitRepositories> {
        return GitHubPagingSource(remoteDataSource)
    }
}
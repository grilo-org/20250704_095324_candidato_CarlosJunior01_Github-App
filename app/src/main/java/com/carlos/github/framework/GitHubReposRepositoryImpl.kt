package com.carlos.github.framework

import androidx.paging.PagingSource
import com.carlos.core.data.repository.GitHubRemoteDataSource
import com.carlos.core.data.repository.GithubReposRepository
import com.carlos.core.domain.model.GitRepositories
import com.carlos.github.framework.network.response.RepositoriesResponseDTO
import javax.inject.Inject

class GitHubReposRepositoryImpl @Inject constructor(
    private val remoteDataSource: GitHubRemoteDataSource<RepositoriesResponseDTO>
): GithubReposRepository {

    override fun getCharacters(query: String): PagingSource<Int, GitRepositories> {
        return GitHubReposPaging()
    }
}
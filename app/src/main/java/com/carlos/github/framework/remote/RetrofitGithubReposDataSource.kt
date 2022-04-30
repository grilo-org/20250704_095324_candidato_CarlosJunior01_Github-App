package com.carlos.github.framework.remote

import com.carlos.core.data.repository.GitHubRemoteDataSource
import com.carlos.github.framework.network.GitHubApi
import com.carlos.github.framework.network.response.RepositoriesResponseDTO
import javax.inject.Inject

class RetrofitGithubReposDataSource @Inject constructor(
    private val gitHubApi: GitHubApi
): GitHubRemoteDataSource<RepositoriesResponseDTO> {

    override suspend fun fetchGithubRepositories(): RepositoriesResponseDTO {
        return gitHubApi.getRepositories()
    }
}
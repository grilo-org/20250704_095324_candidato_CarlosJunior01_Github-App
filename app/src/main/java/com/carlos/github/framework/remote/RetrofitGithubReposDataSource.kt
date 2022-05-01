package com.carlos.github.framework.remote

import com.carlos.core.data.repository.GitHubRemoteDataSource
import com.carlos.github.framework.network.GitHubApi
import com.carlos.github.framework.network.response.GitHubReposResponseDTO
import javax.inject.Inject

class RetrofitGithubReposDataSource @Inject constructor(
    private val gitHubApi: GitHubApi
): GitHubRemoteDataSource<GitHubReposResponseDTO> {

    override suspend fun fetchGithubRepositories(positionPage: Int, limitPage: Int): GitHubReposResponseDTO {
        return gitHubApi.getRepositories(page = positionPage, limitPage = limitPage )
    }
}
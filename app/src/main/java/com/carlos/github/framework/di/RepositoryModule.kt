package com.carlos.github.framework.di

import com.carlos.core.data.repository.GitHubRemoteDataSource
import com.carlos.core.data.repository.GithubReposRepository
import com.carlos.github.framework.GitHubReposRepositoryImpl
import com.carlos.github.framework.network.response.GitHubReposResponseDTO
import com.carlos.github.framework.remote.RetrofitGithubReposDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindGitHubRepos(repository: GitHubReposRepositoryImpl): GithubReposRepository

    @Binds
    fun bindRemoteDataSource(dataSource: RetrofitGithubReposDataSource): GitHubRemoteDataSource<GitHubReposResponseDTO>
}
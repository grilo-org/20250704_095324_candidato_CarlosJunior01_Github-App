package com.carlos.core.data.repository

interface GitHubRemoteDataSource<T> {

    suspend fun fetchGithubRepositories(): T
}
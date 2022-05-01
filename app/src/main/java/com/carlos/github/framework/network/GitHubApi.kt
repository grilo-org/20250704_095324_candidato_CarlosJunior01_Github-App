package com.carlos.github.framework.network

import com.carlos.github.framework.network.response.GitHubReposResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String = "language:kotlin",
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int,
        @Query("per_page") limitPage: Int,
    ) : GitHubReposResponseDTO
}

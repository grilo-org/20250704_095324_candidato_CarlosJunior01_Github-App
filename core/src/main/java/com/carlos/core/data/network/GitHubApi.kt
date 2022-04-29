package com.carlos.core.data.network

import com.carlos.core.data.network.response.RepositoriesResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String = "language:kotlin",
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int = 1,
        @Query("per_page") limitPage: Int = 20,
    ) : RepositoriesResponseDTO
}

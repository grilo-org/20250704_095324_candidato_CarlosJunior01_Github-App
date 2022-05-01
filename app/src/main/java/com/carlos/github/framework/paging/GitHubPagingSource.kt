package com.carlos.github.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.carlos.core.data.repository.GitHubRemoteDataSource
import com.carlos.core.domain.model.GitRepositories
import com.carlos.github.framework.network.response.GitHubReposResponseDTO
import com.carlos.github.framework.network.response.toGitRepositoriesModel
import java.lang.Exception

class GitHubPagingSource(
    private val remoteDataSource: GitHubRemoteDataSource<GitHubReposResponseDTO>
) : PagingSource<Int, GitRepositories>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitRepositories> {
        return try {
            val positionPage = params.key ?: INITIAL_PAGE_INDEX
            val response = remoteDataSource.fetchGithubRepositories(positionPage = positionPage, limitPage = LIMIT)

            LoadResult.Page(
                data = response.items.map { it.toGitRepositoriesModel() },
                prevKey = null,
                nextKey = if (response.items.isEmpty()) null else positionPage + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GitRepositories>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    companion object {
        private const val INITIAL_PAGE_INDEX: Int = 1
        private const val LIMIT = 20
    }
}
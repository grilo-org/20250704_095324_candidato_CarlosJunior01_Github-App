package com.carlos.testing.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.carlos.core.domain.model.GitRepositories

class PagingSourceFactory {

    fun create(heroes: List<GitRepositories>) = object : PagingSource<Int, GitRepositories>() {
        override fun getRefreshKey(state: PagingState<Int, GitRepositories>) = 1

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitRepositories> {
            return LoadResult.Page(
                data = heroes,
                prevKey = null,
                nextKey = 20
            )
        }
    }
}
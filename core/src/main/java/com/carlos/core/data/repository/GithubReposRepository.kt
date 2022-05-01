package com.carlos.core.data.repository

import androidx.paging.PagingSource
import com.carlos.core.domain.model.GitRepositories

interface GithubReposRepository {

    fun getRepositories(query: String): PagingSource<Int, GitRepositories>
}
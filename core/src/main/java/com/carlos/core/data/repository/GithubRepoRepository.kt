package com.carlos.core.data.repository

import androidx.paging.PagingSource
import com.carlos.core.domain.model.GitRepositories

interface GithubRepoRepository {

    fun getCharacters(query: String): PagingSource<Int, GitRepositories>
}
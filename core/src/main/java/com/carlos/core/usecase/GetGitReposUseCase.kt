package com.carlos.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.carlos.core.data.repository.GithubReposRepository
import com.carlos.core.domain.model.GitRepositories
import com.carlos.core.usecase.GetGitReposUseCase.GetGitReposParams
import com.carlos.core.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetGitReposUseCase {
    operator fun invoke(params: GetGitReposParams): Flow<PagingData<GitRepositories>>
    data class GetGitReposParams(val query: String, val pagingConfig: PagingConfig)
}

class GetGitReposUseCaseImpl @Inject constructor(
    private val githubReposRepository: GithubReposRepository
) : PagingUseCase<GetGitReposParams, GitRepositories>(), GetGitReposUseCase {

    override fun createFlowObservable(params: GetGitReposParams): Flow<PagingData<GitRepositories>> {
        val pagingSource = githubReposRepository.getRepositories(params.query)
        return Pager(config = params.pagingConfig) {pagingSource}.flow
    }
}
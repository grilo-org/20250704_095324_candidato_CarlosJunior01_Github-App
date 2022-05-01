package com.carlos.github.presentation.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.carlos.core.domain.model.GitRepositories
import com.carlos.core.usecase.GetGitReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val getGitReposUseCase: GetGitReposUseCase
): ViewModel() {

    fun gitRepositoriesPagingData(query: String): Flow<PagingData<GitRepositories>> {
        return getGitReposUseCase(
            GetGitReposUseCase.GetGitReposParams(query, getPageConfig())
        ).cachedIn(viewModelScope)
    }

    private fun getPageConfig() = PagingConfig(
        pageSize = 20
    )
}
package com.carlos.github.presentation.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.carlos.core.usecase.GetGitReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val getGitReposUseCase: GetGitReposUseCase
): ViewModel() {

    private val _screenState = MutableStateFlow<StateResponse>(
        StateResponse.StateLoading)
    val screenState: StateFlow<StateResponse> = _screenState

    init {
        gitRepositoriesPagingData("")
    }

     fun gitRepositoriesPagingData(query: String) {
         viewModelScope.launch {
             getGitReposUseCase(
                 GetGitReposUseCase.GetGitReposParams(query, getPageConfig())
             ).cachedIn(viewModelScope).collectLatest {
                 _screenState.value = StateResponse.StateSuccess(it)
             }
         }
    }

    private fun getPageConfig() = PagingConfig(
        pageSize = 20
    )
}
package com.carlos.github.presentation.github

import androidx.paging.PagingData
import com.carlos.core.domain.model.GitRepositories

sealed class StateResponse {
    data class StateSuccess(val it: PagingData<GitRepositories>) : StateResponse()
    object StateLoading: StateResponse()
}

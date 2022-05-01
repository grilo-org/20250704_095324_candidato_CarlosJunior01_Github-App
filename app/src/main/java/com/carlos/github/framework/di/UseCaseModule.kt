package com.carlos.github.framework.di

import com.carlos.core.usecase.GetGitReposUseCase
import com.carlos.core.usecase.GetGitReposUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGitRepositoriesUseCase(useCase: GetGitReposUseCaseImpl): GetGitReposUseCase
}
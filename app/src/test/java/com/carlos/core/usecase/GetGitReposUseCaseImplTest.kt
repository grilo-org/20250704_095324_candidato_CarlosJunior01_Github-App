package com.carlos.core.usecase

import androidx.paging.PagingConfig
import com.carlos.core.data.repository.GithubReposRepository
import com.carlos.testing.MainCoroutineRule
import com.carlos.testing.model.GitRepositoriesFactory
import com.carlos.testing.pagingsource.PagingSourceFactory
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetGitReposUseCaseImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: GithubReposRepository

    private lateinit var getGitReposUseCase: GetGitReposUseCase

    private val  githubRepository = GitRepositoriesFactory().create(GitRepositoriesFactory.GithubRepositories.Mvvm)

    private val pagingSourceFactory = PagingSourceFactory().create(listOf(githubRepository))

    @Before
    fun setUp() {
        getGitReposUseCase = GetGitReposUseCaseImpl(repository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() =
        runTest {
            whenever(repository.getRepositories(""))
                .thenReturn(pagingSourceFactory)

            val result = getGitReposUseCase
                .invoke(GetGitReposUseCase.GetGitReposParams("", PagingConfig(20)))

            verify(repository).getRepositories("")
            Assert.assertNotNull(result.first())
        }
}

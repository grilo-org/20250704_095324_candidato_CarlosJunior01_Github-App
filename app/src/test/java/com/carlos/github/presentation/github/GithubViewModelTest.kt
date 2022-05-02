package com.carlos.github.presentation.github

import androidx.paging.PagingData
import com.carlos.core.usecase.GetGitReposUseCase
import com.carlos.testing.MainCoroutineRule
import com.carlos.testing.model.GitRepositoriesFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GithubViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var getGitReposUseCase: GetGitReposUseCase

    private lateinit var githubViewModel: GithubViewModel
    private val githubRepositoriesFactory = GitRepositoriesFactory()

    private val gitRepositoriesFake = PagingData.from(
        listOf(
            githubRepositoriesFactory.create(GitRepositoriesFactory.GithubRepositories.Mvvm),
            githubRepositoriesFactory.create(GitRepositoriesFactory.GithubRepositories.CleanArch)
        )
    )

    @Before
    fun setUp() {
        githubViewModel = GithubViewModel(getGitReposUseCase)
    }

    @Test
    fun `should validate the paging data object values when calling gitRepositoriesPagingData`() =
        runTest {
            whenever(
                getGitReposUseCase.invoke(any())
            ).thenReturn(
                flowOf(
                    gitRepositoriesFake
                )
            )
            val result = githubViewModel.gitRepositoriesPagingData("")

            assertNotNull(result)
        }
}
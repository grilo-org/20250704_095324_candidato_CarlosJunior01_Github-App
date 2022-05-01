package com.carlos.github.presentation.github

import androidx.paging.PagingData
import com.carlos.core.domain.model.GitRepositories
import com.carlos.core.usecase.GetGitReposUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GithubViewModelTest {

    @ExperimentalCoroutinesApi
    val testDispatcher: TestDispatcher = StandardTestDispatcher()

    private val gitRepositoriesFake = PagingData.from(
        listOf(
            GitRepositories(
                name = "Mvvm-Clean",
                stargazersCount = 0,
                watchersCount = 0,
                forksCount = 0,
                language = "Kotlin",
                login = "Uncle Bob",
                avatarUrl = "",
                htmlUrl = ""
            )
        )
    )

    @Mock
    lateinit var getGitReposUseCase: GetGitReposUseCase
    private lateinit var githubViewModel: GithubViewModel

    @Before
    @ExperimentalCoroutinesApi
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        githubViewModel = GithubViewModel(getGitReposUseCase)
    }

    @Test
    @ExperimentalCoroutinesApi
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

            assertNotNull(result.first())
        }

    @Test(expected = RuntimeException::class)
    fun `should throw an exception when the calling to the use case returns an exception`() =
        runTest {
            whenever(getGitReposUseCase.invoke(any()))
                .thenThrow(RuntimeException())

            githubViewModel.gitRepositoriesPagingData("")
        }
}
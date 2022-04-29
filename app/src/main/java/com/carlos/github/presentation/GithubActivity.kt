package com.carlos.github.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.carlos.core.domain.model.GitRepositories
import com.carlos.github.databinding.ActivityGithubBinding
import com.carlos.github.presentation.github.GithubRepositoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GithubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGithubBinding
    private val githubAdapter = GithubRepositoriesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGithubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideActionBar()
        switchFlipperChild()
        initGitRepositoriesAdapter()

        githubAdapter.submitList(
            listOf(
                GitRepositories(name = "Clean Architecture", stargazersCount = 1234, watchersCount = 3456, forksCount = 123, language = "", login = "Uncle Bob", avatarUrl = "https://avatars.githubusercontent.com/u/32689599?v=4", htmlUrl = "" ),
                GitRepositories(name = "Clean Architecture", stargazersCount = 1234, watchersCount = 3456, forksCount = 123, language = "", login = "Uncle Bob", avatarUrl = "https://avatars.githubusercontent.com/u/32689599?v=4", htmlUrl = "" ),
                GitRepositories(name = "Clean Architecture", stargazersCount = 1234, watchersCount = 3456, forksCount = 123, language = "", login = "Uncle Bob", avatarUrl = "https://avatars.githubusercontent.com/u/32689599?v=4", htmlUrl = "" ),
                GitRepositories(name = "Clean Architecture", stargazersCount = 1234, watchersCount = 3456, forksCount = 123, language = "", login = "Uncle Bob", avatarUrl = "https://avatars.githubusercontent.com/u/32689599?v=4", htmlUrl = "" ),
                GitRepositories(name = "Clean Architecture", stargazersCount = 1234, watchersCount = 3456, forksCount = 123, language = "", login = "Uncle Bob", avatarUrl = "https://avatars.githubusercontent.com/u/32689599?v=4", htmlUrl = "" ),
                GitRepositories(name = "Clean Architecture", stargazersCount = 1234, watchersCount = 3456, forksCount = 123, language = "", login = "Uncle Bob", avatarUrl = "https://avatars.githubusercontent.com/u/32689599?v=4", htmlUrl = "" ),
                GitRepositories(name = "Clean Architecture", stargazersCount = 1234, watchersCount = 3456, forksCount = 123, language = "", login = "Uncle Bob", avatarUrl = "https://avatars.githubusercontent.com/u/32689599?v=4", htmlUrl = "" ),
                GitRepositories(name = "Clean Architecture", stargazersCount = 1234, watchersCount = 3456, forksCount = 123, language = "", login = "Uncle Bob", avatarUrl = "https://avatars.githubusercontent.com/u/32689599?v=4", htmlUrl = "" ),
                GitRepositories(name = "Clean Architecture", stargazersCount = 1234, watchersCount = 3456, forksCount = 123, language = "", login = "Uncle Bob", avatarUrl = "https://avatars.githubusercontent.com/u/32689599?v=4", htmlUrl = "" ),
                GitRepositories(name = "Clean Architecture", stargazersCount = 1234, watchersCount = 3456, forksCount = 123, language = "", login = "Uncle Bob", avatarUrl = "https://avatars.githubusercontent.com/u/32689599?v=4", htmlUrl = "" ),
            )
        )
    }

    private fun hideActionBar() {
        supportActionBar?.hide()
    }

    private fun switchFlipperChild() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(THREE_SECONDS)
            binding.viewFlipperActivity.displayedChild = SHOW_CHILD_ONE
        }
    }

    private fun initGitRepositoriesAdapter() {
        binding.repositoriesScreen.recyclerViewRepositories.run {
            setHasFixedSize(true)
            adapter = githubAdapter
        }
    }

    companion object {
        private const val THREE_SECONDS = 3000L
        private const val SHOW_CHILD_ONE = 1
    }
}
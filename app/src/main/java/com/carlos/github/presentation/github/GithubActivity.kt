package com.carlos.github.presentation.github

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.carlos.github.databinding.ActivityGithubBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GithubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGithubBinding
    private val githubAdapter = GithubRepositoriesAdapter()

    private val viewModel: GithubViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGithubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideActionBar()
        switchFlipperChild()
        initGitRepositoriesAdapter()

        lifecycleScope.launchWhenCreated {
            viewModel.gitRepositoriesPagingData("").collect{ pagingData ->
                githubAdapter.submitData(pagingData)
            }
        }
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
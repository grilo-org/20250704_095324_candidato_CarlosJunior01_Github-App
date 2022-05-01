package com.carlos.github.presentation.github

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.carlos.github.databinding.ActivityGithubBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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
        clickListeners()
        observeStateLoad()
        getGithubRepositories()
        initGitRepositoriesAdapter()
    }

    private fun hideActionBar() {
        supportActionBar?.hide()
    }

    private fun clickListeners() {
        binding.errorScreen.btnTryAgain.setOnClickListener {
            getGithubRepositories()
            observeStateLoad()
        }
    }

    private fun observeStateLoad() {
        lifecycleScope.launch {
            githubAdapter.loadStateFlow.collectLatest { loadState ->
                when (loadState.refresh) {
                    is LoadState.Loading -> switchFlipperChild(SHOW_CHILD_ZERO)
                    is LoadState.NotLoading -> switchFlipperChild(SHOW_CHILD_ONE)
                    is LoadState.Error -> switchFlipperChild(SHOW_CHILD_TWO)
                }
            }
        }
    }

    private fun getGithubRepositories() {
        lifecycleScope.launchWhenCreated {
            viewModel.gitRepositoriesPagingData("").collect{ pagingData ->
                githubAdapter.submitData(pagingData)
            }
        }
    }

    private fun initGitRepositoriesAdapter() {
        binding.repositoriesScreen.recyclerViewRepositories.run {
            setHasFixedSize(true)
            adapter = githubAdapter
        }
    }

    private fun switchFlipperChild(childState: Int) {
        when (childState) {
            SHOW_CHILD_ZERO -> binding.viewFlipperActivity.displayedChild = SHOW_CHILD_ZERO
            SHOW_CHILD_ONE -> binding.viewFlipperActivity.displayedChild = SHOW_CHILD_ONE
            SHOW_CHILD_TWO -> binding.viewFlipperActivity.displayedChild = SHOW_CHILD_TWO
        }
    }

    companion object {
        private const val SHOW_CHILD_ZERO = 0
        private const val SHOW_CHILD_ONE = 1
        private const val SHOW_CHILD_TWO = 2
    }
}
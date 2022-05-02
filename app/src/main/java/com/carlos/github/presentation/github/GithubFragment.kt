package com.carlos.github.presentation.github

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.carlos.github.databinding.FragmentGithubBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GithubFragment : Fragment() {

    private lateinit var binding: FragmentGithubBinding
    private val githubAdapter = GithubRepositoriesAdapter()

    private val viewModel: GithubViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentGithubBinding.inflate(inflater, container, false)
        .apply { binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
        observeStateLoad()
        collectStateLoad()
        initGitRepositoriesAdapter()
    }

    private fun clickListeners() {
        binding.errorScreen.btnTryAgain.setOnClickListener {
            viewModel.gitRepositoriesPagingData("")
            collectStateLoad()
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

    private fun collectStateLoad() {
        lifecycleScope.launchWhenCreated {
            viewModel.screenState.collect { state ->
                when (state) {
                    is StateResponse.StateSuccess -> githubAdapter.submitData(state.it)
                    else -> {}
                }
            }
        }
    }

    private fun initGitRepositoriesAdapter() {
        binding.repositoriesScreen.recyclerViewRepositories.run {
            setHasFixedSize(true)
            adapter = githubAdapter.withLoadStateFooter(
                footer = GitReposLoadStateAdapter(
                    githubAdapter::retry
                )
            )
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
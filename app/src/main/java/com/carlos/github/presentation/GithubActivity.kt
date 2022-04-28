package com.carlos.github.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.carlos.github.databinding.ActivityGithubBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GithubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGithubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGithubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        switchFlipperChild()
        hideActionBar()
    }

    private fun switchFlipperChild() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(THREE_SECONDS)
            binding.viewFlipperActivity.displayedChild = SHOW_CHILD_ONE
        }
    }

    private fun hideActionBar() {
        supportActionBar?.hide()
    }

    companion object {
        private const val THREE_SECONDS = 3000L
        private const val SHOW_CHILD_ONE = 1
    }
}
package com.carlos.github.presentation.github

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.carlos.github.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubActivity : AppCompatActivity(R.layout.activity_github) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val fragment: androidx.fragment.app.Fragment
            fragment = GithubFragment()
            loadFragment(fragment)
        }
        hideActionBar()
    }

    private fun loadFragment(fragment: androidx.fragment.app.Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    private fun hideActionBar() {
        supportActionBar?.hide()
    }
}
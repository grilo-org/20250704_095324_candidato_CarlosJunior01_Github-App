package com.carlos.github.presentation.github

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.carlos.github.R
import com.carlos.github.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class GithubFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        launchFragmentInHiltContainer<GithubFragment>()
    }

    @Test
    fun shouldShowInitialScreen_whenViewIsCreated() {
        onView(withId(R.id.initial_screen)
        ).check(matches(isDisplayed()))
    }

    @Test
    fun shouldShowAnimationView_whenViewIsCreated() {
        onView(withId(R.id.animationView)
        ).check(matches(isDisplayed()))
    }

    @Test
    fun shouldShowImageGithub_whenViewIsCreated() {
        onView(withId(R.id.imgGithub)
        ).check(matches(isDisplayed()))
    }

    @Test
    fun shouldHideErrorScreen_whenViewIsCreated() {
        onView(withId(R.id.error_screen)
        ).check(matches(not(isDisplayed())))
    }

    @Test
    fun shouldHideCharacters_whenViewIsCreated() {
        onView(withId(R.id.recycler_view_repositories)
        ).check(matches(not(isDisplayed())))
    }
}
package com.carlos.testing.model

import com.carlos.core.domain.model.GitRepositories

class GitRepositoriesFactory {

    fun create(repositories: GithubRepositories) = when (repositories) {
        GithubRepositories.Mvvm -> GitRepositories(
            name = "Mvvm",
            stargazersCount = 0,
            watchersCount = 0,
            forksCount = 0,
            language = "Swift",
            login = "John Goshmann",
            avatarUrl = "",
            htmlUrl = ""
        )
        GithubRepositories.CleanArch -> GitRepositories(
            name = "Clean-Architecture",
            stargazersCount = 0,
            watchersCount = 0,
            forksCount = 0,
            language = "Kotlin",
            login = "Uncle Bob",
            avatarUrl = "",
            htmlUrl = ""
        )
    }

    sealed class GithubRepositories() {
        object Mvvm: GithubRepositories()
        object CleanArch: GithubRepositories()
    }
}
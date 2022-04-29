package com.carlos.core.domain.model

data class GitRepositories(
    val name: String?,
    val stargazersCount: Int?,
    val watchersCount: Int?,
    val forksCount: Int?,
    val language: String?,
    val login: String?,
    val avatarUrl: String?,
    val htmlUrl: String?
)

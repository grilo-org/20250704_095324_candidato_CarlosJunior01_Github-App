package com.carlos.core.data.network.response

import com.google.gson.annotations.SerializedName

data class RepositoriesResponseDTO(
    @SerializedName("total_count") val totalCount: Int?,
    @SerializedName("items") val items: List<Items?>
)

data class Items(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("full_name") val fullName: String?,
    @SerializedName("owner") val owner: Owner?,
    @SerializedName("description") val description: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    @SerializedName("stargazers_count") val stargazersCount: Int?,
    @SerializedName("watchers_count") val watchersCount: Int?,
    @SerializedName("forks_count") val forksCount: String?,
    @SerializedName("language") val language: String?
)

data class Owner(
    @SerializedName("login") val login: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("html_url") val htmlUrl: String?
)

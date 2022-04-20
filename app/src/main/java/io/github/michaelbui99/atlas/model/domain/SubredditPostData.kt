package io.github.michaelbui99.atlas.model.domain

data class SubredditPostData(
    val title: String?,
    val linkFlairText: String?,
    val textContent: String?,
    val subredditName: String,
    val postAuthor: String,
    val mediaContent: String? = null,
    val topLevelComments: MutableList<Comment>? = mutableListOf()
)

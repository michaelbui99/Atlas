package io.github.michaelbui99.atlas.model.domain

data class Subreddit(
    val headerImageUrl: String?,
    val title: String,
    val displayName: String = title,
    val iconImageUrl: String,
    val subscribers: Long,
    val userIsSubscriber: Boolean = false,
    val subredditVisibility: SubredditVisibility,
    val description: String?
) {

}

enum class SubredditVisibility(val displayName: String) {
    PUBLIC("Public"),
    PRIVATE("Private")
}
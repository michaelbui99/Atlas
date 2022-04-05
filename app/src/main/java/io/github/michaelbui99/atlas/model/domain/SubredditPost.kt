package io.github.michaelbui99.atlas.model.domain

/**
 * A subreddit post a user will see, when inside e.g. reddit.com/r/Art
 * */
data class SubredditPost(
    val subredditName: String,
    val postTitle: String,
    val visited: Boolean,
    val postId: String,
    val upVoteCount: Long,
    val commentCount: Long,
    val thumbnailUrl: String? = null,
    val postScore: Long,
    val authorName: String,
    val mediaUrl: String?,
    val totalAwardsReceived: Int,
    val sourceDomain: String = "self",
    val destination: String? = null,
    val createdUTC: String

) {
}
package io.github.michaelbui99.atlas.model.domain

data class Comment(
    val depth: Long? = 0,
    var replies: MutableList<Comment> = mutableListOf(),
    val author: String?,
    val score: Long = 0,
    val commentText: String? = ""
) {
}
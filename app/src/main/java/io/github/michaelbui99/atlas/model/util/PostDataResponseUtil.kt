package io.github.michaelbui99.atlas.model.util

import io.github.michaelbui99.atlas.model.domain.Comment
import io.github.michaelbui99.atlas.model.network.responseobjects.NestedReplies
import io.github.michaelbui99.atlas.model.network.responseobjects.SubredditPostDataResponse
import io.github.michaelbui99.atlas.model.network.responseobjects.TopLevelReplies

// TODO: Implement a way to get replies of a post data response comment

fun getComments(postDataResponse: SubredditPostDataResponse): MutableList<Comment> {
    val topLevelComments = mutableListOf<Comment>()

    postDataResponse.data[1].data.children.forEach {
        val comment = Comment(
            depth = it.data.depth,
            author = it.data.author,
            score = it.data.score,
            commentText = it.data.body
        )

        val commentReplies = mutableListOf<Comment>()
        if (it.data.replies !is String && it.data.replies != null) {
            getCommentsHelper(commentReplies, it.data.replies as TopLevelReplies)
        }
        comment.replies.addAll(commentReplies)
        topLevelComments.add(comment)
    }

    return topLevelComments
}

private fun getCommentsHelper(comments: MutableList<Comment>, nestedReplies: TopLevelReplies) {
    if (nestedReplies.data.children.isEmpty()) {
        return
    }

    val commentReplies = mutableListOf<Comment>()
    nestedReplies.data.children.forEach {
        val comment = Comment(
            depth = it.data?.depth,
            author = it.data?.author,
            score = it.data!!.score,
            commentText = it.data.body
        )
        comments.add(comment)
        if (it.data.replies !is String && it.data.replies != null) {
            getCommentsHelper(commentReplies, it.data.replies as TopLevelReplies)
        }
        comment.replies.addAll(commentReplies)
    }
}
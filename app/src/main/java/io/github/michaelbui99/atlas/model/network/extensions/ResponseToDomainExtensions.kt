package io.github.michaelbui99.atlas.model.network.extensions

import io.github.michaelbui99.atlas.model.domain.*
import io.github.michaelbui99.atlas.model.domain.user.RedditUser
import io.github.michaelbui99.atlas.model.network.responseobjects.*
import io.github.michaelbui99.atlas.model.util.convertUnixToLocalDateTime
import io.github.michaelbui99.atlas.model.util.getComments

fun DefaultSubredditsResponse.toDomainObject(): MutableList<Subreddit> {
    val defaultSubredditList = mutableListOf<Subreddit>()

    this.data.children.forEach {
        val subreddit = Subreddit(
            title = it.data.title,
            displayName = it.data.displayName,
            headerImageUrl = it.data.headerImg,
            iconImageUrl = it.data.iconImg,
            subscribers = it.data.subscribers,
            userIsSubscriber = it.data.userIsSubscriber,
            subredditVisibility = it.data.subredditType,
            description = it.data.description
        )

        defaultSubredditList.add(subreddit)
    }

    return defaultSubredditList
}


fun SubredditResponse.toDomainObject(): MutableList<SubredditPost> {
    val subredditPosts = mutableListOf<SubredditPost>()

    data.children.forEach() {
        val subredditPost = SubredditPost(
            subredditName = it.data.subreddit,
            postTitle = it.data.title,
            visited = it.data.clicked,
            postId = it.data.id,
            upVoteCount = it.data.ups,
            commentCount = it.data.numComments,
            thumbnailUrl = it.data.thumbnail,
            postScore = it.data.score,
            authorName = it.data.author,
            mediaUrl = it.data.urlOverriddenByDest,
            totalAwardsReceived = it.data.totalAwardsReceived.toInt(),
            sourceDomain = it.data.domain,
            destination = it.data.url,
            createdUTC = it.data.createdUtc.toString(),
            userHasLiked = it.data.likes as Boolean?
        )
        subredditPosts.add(subredditPost)
    }

    return subredditPosts
}


fun SubredditAboutResponse.toDomainObject(): SubredditAbout {
    return SubredditAbout(
        displayNamePrefixed = data.displayNamePrefixed,
        description = data.description,
        iconImage = data.iconImage,
        subscribers = data.subscribers,
        activeAccounts = data.activeAccounts
    )
}


fun SubredditPostDataResponse.toDomainObject(): SubredditPostData {
    val topLevelComments = mutableListOf<Comment>()
    data[1].data.children.forEach {
        topLevelComments.add(
            Comment(
                depth = it.data.depth,
                author = it.data.author,
                score = it.data.score,
                commentText = it.data.body
            )
        )
    }

    return SubredditPostData(
        title = data[0].data.children[0].data.title,
        linkFlairText = data[0].data.children[0].data.linkFlairText,
        textContent = data[0].data.children[0].data.selfText,
        subredditName = data[0].data.children[0].data.subreddit,
        postAuthor = data[0].data.children[0].data.author,
        mediaContent = data[0].data.children[0].data.urlOverriddenByDest,
        topLevelComments = topLevelComments,
        userHasLiked = data[0].data.children[0].data.likes,
        fullName = data[0].data.children[0].data.name
    )
}

fun MeResponse.toDomainObject(): RedditUser {
    return RedditUser(
        displayName = name,
        iconUrl = iconImageUrl,
        karmaCount = totalKarma,
        createdUtc = convertUnixToLocalDateTime(createdUtc)
    )
}
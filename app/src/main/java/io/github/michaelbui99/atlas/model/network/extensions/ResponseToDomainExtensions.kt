package io.github.michaelbui99.atlas.model.network.extensions

import io.github.michaelbui99.atlas.model.domain.Subreddit
import io.github.michaelbui99.atlas.model.domain.SubredditAbout
import io.github.michaelbui99.atlas.model.domain.SubredditPost
import io.github.michaelbui99.atlas.model.domain.SubredditPostData
import io.github.michaelbui99.atlas.model.network.responseobjects.DefaultSubredditsResponse
import io.github.michaelbui99.atlas.model.network.responseobjects.SubredditAboutResponse
import io.github.michaelbui99.atlas.model.network.responseobjects.SubredditPostDataResponse
import io.github.michaelbui99.atlas.model.network.responseobjects.SubredditResponse

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
    return SubredditPostData(
        title = data[0].data.children[0].data.title,
        linkFlairText = data[0].data.children[0].data.linkFlairText,
        textContent = data[0].data.children[0].data.selfText,
        subredditName = data[0].data.children[0].data.subreddit,
        postAuthor = data[0].data.children[0].data.author
    )
}
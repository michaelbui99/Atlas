package io.github.michaelbui99.atlas.model.network.extensions

import io.github.michaelbui99.atlas.model.domain.Subreddit
import io.github.michaelbui99.atlas.model.network.responseobjects.DefaultSubredditsResponse

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
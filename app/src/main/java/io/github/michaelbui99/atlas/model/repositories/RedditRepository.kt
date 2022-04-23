package io.github.michaelbui99.atlas.model.repositories

import io.github.michaelbui99.atlas.model.domain.*
import io.github.michaelbui99.atlas.model.network.responseobjects.SubredditPostDataResponse
import io.reactivex.rxjava3.core.Flowable

interface RedditRepository {
    fun getDefaultSubreddits(): Flowable<MutableList<Subreddit>>
    fun getSubredditPosts(subreddit: String): Flowable<MutableList<SubredditPost>>
    fun getSubredditPostData(subredditName: String, postId: String): Flowable<SubredditPostData>
    fun getSubredditAbout(subreddit: String): Flowable<SubredditAbout>
    fun getSubscribedSubreddits(): Flowable<MutableList<Subreddit>>
    fun getMe(): Flowable<User>
    fun getMeFrontPage(): Flowable<MutableList<SubredditPost>>
    fun searchForSubreddits(
        searchQuery: String = "",
        exactMatchesOnly: Boolean = false,
        includeOver18Results: Boolean = false
    ): Flowable<MutableList<Subreddit>>
}
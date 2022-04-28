package io.github.michaelbui99.atlas.model.repositories

import io.github.michaelbui99.atlas.model.domain.*
import io.github.michaelbui99.atlas.model.domain.user.RedditUser
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.Query

interface RedditRepository {
    fun getDefaultSubreddits(): Flowable<MutableList<Subreddit>>
    fun getSubredditPosts(subreddit: String): Flowable<MutableList<SubredditPost>>
    fun getSubredditPostData(subredditName: String, postId: String): Flowable<SubredditPostData>
    fun getSubredditAbout(subreddit: String): Flowable<SubredditAbout>
    fun getSubscribedSubreddits(): Flowable<MutableList<Subreddit>>
    fun getMe(): Flowable<RedditUser>
    fun getMeFrontPage(): Flowable<MutableList<SubredditPost>>
    fun searchForSubreddits(
        @Query("q") searchQuery: String = "",
    ): Flowable<MutableList<Subreddit>>
}
package io.github.michaelbui99.atlas.model.repositories

import io.github.michaelbui99.atlas.model.domain.Subreddit
import io.github.michaelbui99.atlas.model.domain.SubredditAbout
import io.github.michaelbui99.atlas.model.domain.SubredditPost
import io.github.michaelbui99.atlas.model.domain.SubredditPostData
import io.github.michaelbui99.atlas.model.network.responseobjects.SubredditPostDataResponse
import io.reactivex.rxjava3.core.Flowable

interface SubredditRepository {
    // TODO: Write mapper to map HttpResponseObject to domain objects.
    fun getDefaultSubreddits(): Flowable<MutableList<Subreddit>>
    fun getSubredditPosts(subreddit: String): Flowable<MutableList<SubredditPost>>
    fun getSubredditPostData(subredditName: String, postId: String): Flowable<SubredditPostData>
    fun getSubredditAbout(subreddit: String): Flowable<SubredditAbout>
    fun getSubscribedSubreddits(): Flowable<MutableList<Subreddit>>
}
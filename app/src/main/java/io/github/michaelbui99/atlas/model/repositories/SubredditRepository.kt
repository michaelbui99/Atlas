package io.github.michaelbui99.atlas.model.repositories

import io.github.michaelbui99.atlas.model.domain.Subreddit
import io.github.michaelbui99.atlas.model.network.responseobjects.SubredditPostDataResponse
import io.reactivex.rxjava3.core.Flowable

interface SubredditRepository {
    // TODO: Write mapper to map HttpResponseObject to domain objects.
    fun getDefaultSubreddits(): Flowable<Subreddit>
    fun getSubredditPosts(subreddit: String)
    fun getSubredditPostData(): SubredditPostDataResponse
}
package io.github.michaelbui99.atlas.model.repositories

import androidx.lifecycle.MutableLiveData
import io.github.michaelbui99.atlas.model.domain.Subreddit
import io.github.michaelbui99.atlas.model.network.responseobjects.SubredditPostDataResponse

interface SubredditRepository {
    // TODO: Write mapper to map HttpResponseObject to domain objects.
    fun getDefaultSubreddits(): Unit
    fun getSubredditPosts(subreddit: String)
    fun getSubredditPostData(): SubredditPostDataResponse
}
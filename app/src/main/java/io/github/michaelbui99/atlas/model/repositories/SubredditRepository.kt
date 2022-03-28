package io.github.michaelbui99.atlas.model.repositories

import io.github.michaelbui99.atlas.model.http.responseobjects.SubredditPostDataResponse

interface SubredditRepository {
    // TODO: Write mapper to map HttpResponseObject to domain objects.
    //  Refactor methods to return domain objects instead.
    fun getDefaultSubreddits()
    fun getSubredditPosts(subreddit: String)
    fun getSubredditPostData(): SubredditPostDataResponse
}
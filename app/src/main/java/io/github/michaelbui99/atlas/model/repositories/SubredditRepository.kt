package io.github.michaelbui99.atlas.model.repositories

import io.github.michaelbui99.atlas.model.http.responseobjects.SubredditPostDataResponse

interface SubredditRepository {

    fun getDefaultSubreddits()
    fun getSubredditPosts(subreddit: String)
    fun getSubredditPostData(): SubredditPostDataResponse
}
package io.github.michaelbui99.atlas.model.repositories

interface SubredditRepository {

    fun getDefaultSubreddits()
    fun getSubredditPosts(subreddit: String)
    fun getSubredditThreadData()
}
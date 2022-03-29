package io.github.michaelbui99.atlas.model.repositories

import io.github.michaelbui99.atlas.model.network.RedditClient
import io.github.michaelbui99.atlas.model.network.responseobjects.SubredditPostDataResponse

class SubredditRepositoryImpl : SubredditRepository{
    private val redditClient: RedditClient = RedditClient()

    override fun getDefaultSubreddits() {
        // TODO: Cache requests for default subreddits, since these don't change often
        TODO("Not yet implemented")
    }

    override fun getSubredditPosts(subreddit: String) {
        TODO("Not yet implemented")
    }

    override fun getSubredditPostData(): SubredditPostDataResponse {
        TODO("Not yet implemented")
    }
}
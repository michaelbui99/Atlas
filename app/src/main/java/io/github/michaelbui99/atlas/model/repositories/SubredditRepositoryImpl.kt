package io.github.michaelbui99.atlas.model.repositories

import io.github.michaelbui99.atlas.model.domain.Subreddit
import io.github.michaelbui99.atlas.model.network.RedditClient
import io.github.michaelbui99.atlas.model.network.extensions.toDomainObject
import io.github.michaelbui99.atlas.model.network.responseobjects.SubredditPostDataResponse
import io.reactivex.rxjava3.core.Flowable

object SubredditRepositoryImpl : SubredditRepository {
    private val redditClient: RedditClient = RedditClient()
    // TODO: Refactor LiveData to something else. Should not use Lifecycle aware objects in repository

    init {
    }

    override fun getDefaultSubreddits(): Flowable<Subreddit> {
        // TODO: Cache requests for default subreddits, since these don't change often
        // TODO: Fetch from DAO Instead
         return redditClient.redditAPI().getDefaultSubreddits().flatMap {
            Flowable.fromIterable(it.toDomainObject())
        }
    }

    override fun getSubredditPosts(subreddit: String) {
        TODO("Not yet implemented")
    }

    override fun getSubredditPostData(): SubredditPostDataResponse {
        TODO("Not yet implemented")
    }
}

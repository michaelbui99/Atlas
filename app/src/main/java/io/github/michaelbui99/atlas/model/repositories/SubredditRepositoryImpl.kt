package io.github.michaelbui99.atlas.model.repositories

import io.github.michaelbui99.atlas.model.domain.Subreddit
import io.github.michaelbui99.atlas.model.network.RedditClient
import io.github.michaelbui99.atlas.model.network.extensions.toDomainObject
import io.github.michaelbui99.atlas.model.network.responseobjects.SubredditPostDataResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.kotlin.subscribeBy

object SubredditRepositoryImpl : SubredditRepository {
    private val redditClient: RedditClient = RedditClient()

    init {
    }

    override fun getDefaultSubreddits(): Flowable<MutableList<Subreddit>> {
        // TODO: Cache requests for default subreddits, since these don't change often
        // TODO: Fetch from DAO Instead

        return redditClient.redditAPI().getDefaultSubreddits().flatMap {
            Flowable.just(it.toDomainObject())
        }

    }

    override fun getSubredditPosts(subreddit: String) {
        TODO("Not yet implemented")
    }

    override fun getSubredditPostData(): SubredditPostDataResponse {
        TODO("Not yet implemented")
    }
}

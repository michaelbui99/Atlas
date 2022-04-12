package io.github.michaelbui99.atlas.model.repositories

import android.content.SharedPreferences
import io.github.michaelbui99.atlas.model.domain.Subreddit
import io.github.michaelbui99.atlas.model.domain.SubredditAbout
import io.github.michaelbui99.atlas.model.domain.SubredditPost
import io.github.michaelbui99.atlas.model.domain.SubredditPostData
import io.github.michaelbui99.atlas.model.network.RedditClient
import io.github.michaelbui99.atlas.model.network.extensions.toDomainObject
import io.github.michaelbui99.atlas.model.network.responseobjects.SubredditPostDataResponse
import io.github.michaelbui99.atlas.model.util.convertUnixToLocalDate
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

object SubredditRepositoryImpl : SubredditRepository {
    private val redditClient: RedditClient = RedditClient()

    init {
    }

    override fun getDefaultSubreddits(): Flowable<MutableList<Subreddit>> {
        // TODO: Cache requests for default subreddits, since these don't change often
        // TODO: Fetch from DAO Instead

        return redditClient.redditAPI().getDefaultSubreddits().subscribeOn(Schedulers.io())
            .flatMap {
                Flowable.just(it.toDomainObject())
            }

    }

    override fun getSubredditPosts(subreddit: String): Flowable<MutableList<SubredditPost>> {
        return redditClient.redditAPI().getSubredditPosts(subredditName = subreddit)
            .subscribeOn(Schedulers.io()).flatMap {
                val posts = it.toDomainObject()
                posts.forEach { post ->
                    post.createdUTC = convertUnixToLocalDate(post.createdUTC.toLong()).toString()
                }
                Flowable.just(posts)
            }

    }

    override fun getSubredditPostData(
        subredditName: String,
        postId: String
    ): Flowable<SubredditPostData> {
        return redditClient.redditAPI()
            .getSubredditPostData(subredditName = subredditName, postId = postId)
            .subscribeOn(Schedulers.io()).flatMap {
                Flowable.just(SubredditPostDataResponse(it).toDomainObject())
            }
    }

    override fun getSubredditAbout(subreddit: String): Flowable<SubredditAbout> {
        return redditClient.redditAPI().getSubredditAbout(subredditName = subreddit)
            .subscribeOn(Schedulers.io()).flatMap {
                Flowable.just(it.toDomainObject())
            }
    }
}

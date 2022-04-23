package io.github.michaelbui99.atlas.model.repositories

import android.util.Log
import io.github.michaelbui99.atlas.model.domain.*
import io.github.michaelbui99.atlas.model.network.RedditClient
import io.github.michaelbui99.atlas.model.network.extensions.toDomainObject
import io.github.michaelbui99.atlas.model.network.responseobjects.SubredditPostDataResponse
import io.github.michaelbui99.atlas.model.util.convertUnixToLocalDateTime
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers

object RedditRepositoryImpl : RedditRepository {
    private val redditClient: RedditClient = RedditClient()


    override fun getDefaultSubreddits(): Flowable<MutableList<Subreddit>> {
        // TODO: Cache requests for default subreddits, since these don't change often
        // TODO: Fetch from DAO Instead

        return redditClient.noAuthRedditAPI().getDefaultSubreddits().subscribeOn(Schedulers.io())
            .flatMap {
                Flowable.just(it.toDomainObject())
            }

    }


    override fun getSubredditPosts(subreddit: String): Flowable<MutableList<SubredditPost>> {
        return redditClient.noAuthRedditAPI().getSubredditPosts(subredditName = subreddit)
            .subscribeOn(Schedulers.io()).flatMap {
                val posts = it.toDomainObject()
                posts.forEach { post ->
                    post.createdUTC = convertUnixToLocalDateTime(post.createdUTC.toLong()).toString()
                }
                Flowable.just(posts)
            }
    }


    override fun getSubredditPostData(
        subredditName: String,
        postId: String
    ): Flowable<SubredditPostData> {
        return redditClient.noAuthRedditAPI()
            .getSubredditPostData(subredditName = subredditName, postId = postId)
            .subscribeOn(Schedulers.io()).flatMap {
                Flowable.just(SubredditPostDataResponse(it).toDomainObject())
            }
    }


    override fun getSubredditAbout(subreddit: String): Flowable<SubredditAbout> {
        return redditClient.noAuthRedditAPI().getSubredditAbout(subredditName = subreddit)
            .subscribeOn(Schedulers.io()).flatMap {
                Flowable.just(it.toDomainObject())
            }
    }


    override fun getSubscribedSubreddits(): Flowable<MutableList<Subreddit>> {
        Log.i("SubredditRepository", "Fetching Subscribed Subreddits")
        return redditClient.authRedditAPI().getSubscribedSubreddits().subscribeOn(Schedulers.io())
            .flatMap {
                Flowable.just(it.toDomainObject())
            }
    }

    override fun getMe(): Flowable<User> {
        return redditClient.authRedditAPI().getMe().subscribeOn(Schedulers.io()).flatMap {
            Flowable.just(it.toDomainObject())
        }
    }


    override fun getMeFrontPage(): Flowable<MutableList<SubredditPost>> {
        return if (AuthRepositoryImpl.userIsLoggedIn()){
            redditClient.authRedditAPI().getMeFrontPage().subscribeOn(Schedulers.io()).flatMap {
                Flowable.just(it.toDomainObject())
            }
        }else{
            redditClient.noAuthRedditAPI().getMeFrontPage().subscribeOn(Schedulers.io()).flatMap {
                Flowable.just(it.toDomainObject())
            }
        }
    }


    override fun searchForSubreddits(
        searchQuery: String,
        exactMatchesOnly: Boolean,
        includeOver18Results: Boolean
    ): Flowable<MutableList<Subreddit>> {
        TODO("Implement this")
    }
}

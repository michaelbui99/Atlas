package io.github.michaelbui99.atlas.model.repositories

import android.util.Log
import io.github.michaelbui99.atlas.model.domain.Subreddit
import io.github.michaelbui99.atlas.model.network.RedditClient
import io.github.michaelbui99.atlas.model.network.extensions.toDomainObject
import io.github.michaelbui99.atlas.model.network.responseobjects.DefaultSubredditsResponse
import io.github.michaelbui99.atlas.model.network.responseobjects.SubredditPostDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SubredditRepositoryImpl : SubredditRepository{
    private val redditClient: RedditClient = RedditClient()
    // TODO: Refactor LiveData to something else. Should not use Lifecycle aware objects in repository
    public var defaultSubredditList: MutableList<Subreddit> = mutableListOf()

    init {
    }

    override fun getDefaultSubreddits(): Unit{
        // TODO: Cache requests for default subreddits, since these don't change often
        // TODO: Fetch from DAO Instead
        val call: Call<DefaultSubredditsResponse> = redditClient.redditAPI().getDefaultSubreddits()
        call.enqueue(object: Callback<DefaultSubredditsResponse>{
            override fun onResponse(
                call: Call<DefaultSubredditsResponse>,
                response: Response<DefaultSubredditsResponse>
            ) {
                if (response.isSuccessful)   {
                    defaultSubredditList = response.body()?.toDomainObject() ?: mutableListOf()
                }
            }

            override fun onFailure(call: Call<DefaultSubredditsResponse>, t: Throwable) {
                Log.i("SubredditRepository", "Could not fetch default subreddits")
            }
        })
    }

    override fun getSubredditPosts(subreddit: String) {
        TODO("Not yet implemented")
    }

    override fun getSubredditPostData(): SubredditPostDataResponse {
        TODO("Not yet implemented")
    }
}
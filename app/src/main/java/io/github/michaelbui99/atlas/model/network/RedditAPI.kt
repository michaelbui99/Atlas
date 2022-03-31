package io.github.michaelbui99.atlas.model.network

import io.github.michaelbui99.atlas.model.network.responseobjects.DefaultSubredditsResponse
import io.github.michaelbui99.atlas.model.network.responseobjects.SubredditPostDataResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RedditAPI {
    // TODO: Refactor call type
    @GET("r/{subredditName}.json")
    fun getSubredditPosts(@Path("subredditName") subredditName: String): Call<String>

    // TODO: Refactor call type
    @GET("r/{subredditName}/about.json")
    fun getSubredditAbout(@Path("subredditName") subredditName: String): Call<String>

    @GET("r/{subredditName}/comments/{postId}.json")
    fun getSubredditPostData(
        @Path("subredditName") subredditName: String,
        @Path("postId") postId: String
    ): Flowable<SubredditPostDataResponse>

    // TODO: Refactor call type
    @GET("subreddits/default.json")
    fun getDefaultSubreddits(): Flowable<DefaultSubredditsResponse>
}
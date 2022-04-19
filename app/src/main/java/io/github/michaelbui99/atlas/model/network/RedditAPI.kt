package io.github.michaelbui99.atlas.model.network

import io.github.michaelbui99.atlas.model.network.responseobjects.*
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.Flow

interface RedditAPI {
    /**
     * Fetches the posts of a subreddit.
     * The amount of posts is limited to 100 by default.
     *
     * @param subredditName name of the subreddit
     * @param limit limit of how many posts are to be fetched
     * */
    @GET("r/{subredditName}.json")
    fun getSubredditPosts(
        @Path("subredditName") subredditName: String,
        @Query("limit") limit: Int = 100,
    ): Flowable<SubredditResponse>


    /**
     * Fetches the about (description, rules etc.) of a subreddit
     *
     * @param subredditName name of the subreddit
     * */
    @GET("r/{subredditName}/about.json")
    fun getSubredditAbout(
        @Path("subredditName") subredditName: String
    ): Flowable<SubredditAboutResponse>


    /**
     * Fetches the content of a post including the text content, media content and comments
     *
     * @param subredditName name of the subreddit the posts is created in
     * @param postId id of the post
     * */
    @GET("r/{subredditName}/comments/{postId}.json")
    fun getSubredditPostData(
        @Path("subredditName") subredditName: String,
        @Path("postId") postId: String
    ): Flowable<List<ResponseDataWrapper<ResponseData>>>


    /**
     * Fetches all the default subreddits, i.e. the subreddits everyone is subscribed to in the
     * beginning
     * */
    @GET("subreddits/default.json")
    fun getDefaultSubreddits(): Flowable<DefaultSubredditsResponse>


    /**
     * Fetches all the subreddits that the user is subscribed to.
     * This method limits the amount of subreddits to 100 by default
     *
     * @param limit limit of how many subreddits are to be fetched
     * */
    @GET("subreddits/mine/subscriber")
    fun getSubscribedSubreddits(
        @Query("limit") limit: Int = 100
    ): Flowable<DefaultSubredditsResponse>

    @GET("api/v1/me")
    fun getMe(): Flowable<MeResponse>
}
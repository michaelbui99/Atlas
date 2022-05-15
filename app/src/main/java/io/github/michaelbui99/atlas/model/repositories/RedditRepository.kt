package io.github.michaelbui99.atlas.model.repositories

import io.github.michaelbui99.atlas.model.domain.*
import io.github.michaelbui99.atlas.model.domain.user.RedditUser
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.Query

interface RedditRepository {
    /**
     * Gets the default subreddits that every Reddit User are subscribed to
     *
     * @return Flowable containing list of default subreddits
     * */
    fun getDefaultSubreddits(): Flowable<MutableList<Subreddit>>

    /**
     * Gets a subreddit's posts.
     * The amount of posts fetched is limited by getSubredditPosts in RedditAPI
     * @see io.github.michaelbui99.atlas.model.network.RedditAPI
     *
     * @return Flowable containing list of Subreddit posts
     * */
    fun getSubredditPosts(subreddit: String): Flowable<MutableList<SubredditPost>>

    /**
     * Gets the data for a post such as post content, comments, title etc.
     *
     * @return Flowable containing the Subreddit Post Data
     * */
    fun getSubredditPostData(subredditName: String, postId: String): Flowable<SubredditPostData>

    /**
     * Gets the about information for a subreddit
     *
     * @param subreddit name of the subreddit
     * @return Flowable containing the about
     * */
    fun getSubredditAbout(subreddit: String): Flowable<SubredditAbout>

    /**
     * Gets the subreddits that a User is subscribed to.
     *
     * @return Flowable containing a list of subscribed subreddits
     * */
    fun getSubscribedSubreddits(): Flowable<MutableList<Subreddit>>

    /**
     * Gets the logged in user's information such as karma score, account age, reddit name etc.
     *
     * @return
     * */
    fun getMe(): Flowable<RedditUser>

    /**
     * Gets an aggregated list of subreddit posts from the logged in User's subscribed subreddits
     *
     * @return Flowable containing a list of Subreddit posts
     * */
    fun getMeFrontPage(): Flowable<MutableList<SubredditPost>>

    /**
     * Searches for subreddits that matches a query string
     *
     * @param searchQuery the query that is used to match subreddits
     * @return Flowable containing a list of subreddits
     * */
    fun searchForSubreddits(
        @Query("q") searchQuery: String = "",
    ): Flowable<MutableList<Subreddit>>

    /**
     * Searches for posts inside a subreddit
     *
     * @param subredditName name of the subreddit that is being searched
     * @param searchQuery the query that is used to match posts
     * @return Flowable containing a list of subreddit posts that matches the query
     * */
    fun searchForPostsInSubreddit(
        subredditName: String,
        searchQuery: String = ""
    ): Flowable<MutableList<SubredditPost>>

    /**
     * Vote a subreddit post
     *
     * @param voteDirection determines if the user is up voting, down voting or removing vote
     * @param postId the id if the post
     * */
    fun voteSubredditPost(voteDirection: VoteDirection, postId: String): Flowable<Any>
}
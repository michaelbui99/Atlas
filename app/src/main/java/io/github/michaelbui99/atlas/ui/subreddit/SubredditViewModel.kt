package io.github.michaelbui99.atlas.ui.subreddit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.michaelbui99.atlas.model.domain.Subreddit
import io.github.michaelbui99.atlas.model.domain.SubredditAbout
import io.github.michaelbui99.atlas.model.domain.SubredditPost
import io.github.michaelbui99.atlas.model.repositories.RedditRepositoryImpl
import io.reactivex.rxjava3.kotlin.subscribeBy

class SubredditViewModel : ViewModel() {
    val subredditPosts: MutableLiveData<MutableList<SubredditPost>> = MutableLiveData()
    val subredditAbout: MutableLiveData<SubredditAbout> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    private var currentSubreddit: String = ""

    fun setCurrentSubreddit(subredditName: String) {
        Log.i("SubredditViewModel", "Setting current subreddit to: $subredditName")
        currentSubreddit = subredditName

        if (currentSubreddit == "home") {
            getFrontPagePosts()
            subredditAbout.postValue(
                SubredditAbout(
                    displayNamePrefixed = "r/",
                    description = "Aggregated posts from your subscribed subreddits",
                    subscribers = 0,
                    activeAccounts = 0,
                    iconImage = null
                )
            )
            return
        }

        getSubredditPosts()
        getSubredditAbout()
    }

    fun refreshSubreddit() {
        if (currentSubreddit == "home") {
            getFrontPagePosts()
            getFrontPageAbout()
            return
        }
        Log.i("SubredditViewModel", "Refreshing")
        getSubredditPosts()
        getSubredditAbout()
    }

    private fun getSubredditPosts() {
        if (currentSubreddit.isNotBlank() && currentSubreddit.isNotEmpty()) {
            Log.i("SubredditViewModel", "Fetching posts")
            RedditRepositoryImpl.getSubredditPosts(currentSubreddit).subscribeBy(
                onNext = {
                    subredditPosts.postValue(it)
                },
                onError = {
                    Log.e("SubredditViewModel", "Failed to fetch subreddits posts: $it")
                    error.postValue("Something went wrong... try again later")
                },
                onComplete = {
                    Log.i("SubredditViewModel", "Fetched all posts")
                }
            )
        }
    }


    private fun getFrontPagePosts() {
        if (currentSubreddit.isNotBlank() && currentSubreddit.isNotEmpty()) {
            Log.i("SubredditViewModel", "Fetching posts")
            RedditRepositoryImpl.getMeFrontPage().subscribeBy(
                onNext = {
                    subredditPosts.postValue(it)
                },
                onError = {
                    Log.e("SubredditViewModel", "Failed to fetch subreddits posts: $it")
                    error.postValue("Something went wrong... try again later")
                },
                onComplete = {
                    Log.i("SubredditViewModel", "Fetched all posts")
                }
            )
        }
    }


    private fun getFrontPageAbout() {
        subredditAbout.postValue(
            SubredditAbout(
                displayNamePrefixed = "r/",
                description = "Aggregated posts from your subscribed subreddits",
                subscribers = 0,
                activeAccounts = 0,
                iconImage = null
            )
        )
    }


    private fun getSubredditAbout() {
        if (currentSubreddit.isNotBlank() && currentSubreddit.isNotEmpty()) {
            RedditRepositoryImpl.getSubredditAbout(currentSubreddit).subscribeBy(
                onNext = {
                    subredditAbout.postValue(it)
                },
                onError = {
                    Log.e("SubredditViewModel", "Failed to fetch subreddit about: $it")
                    error.postValue("Something went wrong... try again later")
                }
            )
        }
    }
}
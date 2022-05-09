package io.github.michaelbui99.atlas.ui.subreddit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.michaelbui99.atlas.model.domain.Subreddit
import io.github.michaelbui99.atlas.model.domain.SubredditAbout
import io.github.michaelbui99.atlas.model.domain.SubredditPost
import io.github.michaelbui99.atlas.model.repositories.RedditRepositoryImpl
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.io.FileNotFoundException
import java.lang.IllegalStateException

class SubredditViewModel : ViewModel() {
    val subredditPosts: MutableLiveData<MutableList<SubredditPost>> = MutableLiveData()
    val subredditAbout: MutableLiveData<SubredditAbout> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()
    val isLoadingPosts: MutableLiveData<Boolean> = MutableLiveData(true)
    val shouldDisplaySearch: MutableLiveData<Boolean> = MutableLiveData(false)

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

    fun onSearchMenuItem() {
        shouldDisplaySearch.value = !shouldDisplaySearch.value!!
    }

    fun onViewInit() {
        shouldDisplaySearch.value = false
        isLoadingPosts.value = true
        refreshSubreddit()
    }

    fun searchSubredditPosts(searchQuery: String) {
        if (currentSubreddit.isBlank()) {
            throw IllegalStateException("No subreddit name has been set")
        }

        isLoadingPosts.value = true

        if (searchQuery == "") {
            refreshSubreddit()
            return
        }

        RedditRepositoryImpl.searchForPostsInSubreddit(
            this.currentSubreddit,
            searchQuery = searchQuery
        ).subscribeBy(
            onNext = {
                subredditPosts.postValue(it)
            },
            onError = {
                error.postValue("Something might have went wrong...: ${it.message}")
            },
            onComplete = {
                isLoadingPosts.postValue(false)
            }
        )
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
                    this.isLoadingPosts.postValue(false)
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
                    error.postValue("Something might have went wrong...: ${it.message}")
                },
                onComplete = {
                    Log.i("SubredditViewModel", "Fetched all posts")
                    isLoadingPosts.postValue(false)
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
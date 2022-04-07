package io.github.michaelbui99.atlas.ui.subreddit

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.michaelbui99.atlas.model.domain.SubredditAbout
import io.github.michaelbui99.atlas.model.domain.SubredditPost
import io.github.michaelbui99.atlas.model.repositories.SubredditRepositoryImpl
import io.reactivex.rxjava3.kotlin.subscribeBy

class SubredditViewModel : ViewModel() {
    val subredditPosts: MutableLiveData<MutableList<SubredditPost>> = MutableLiveData()
    val subredditAbout: MutableLiveData<SubredditAbout> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    private var currentSubreddit: String = ""

    fun setCurrentSubreddit(subredditName: String) {
        Log.i("SubredditViewModel", "Setting current subreddit to: $subredditName")
        currentSubreddit = subredditName
        fetchSubredditPosts()
        fetchSubredditAbout()
    }

    private fun fetchSubredditPosts() {
        if (currentSubreddit.isNotBlank() && currentSubreddit.isNotEmpty()) {
            Log.i("SubredditViewModel", "Fetching posts")
            SubredditRepositoryImpl.getSubredditPosts(currentSubreddit).subscribeBy(
                onNext = {
                    it.forEach() { post ->
                        Log.i("SubredditViewModel", "Fetched post: ${post.postTitle}")
                    }
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

    private fun fetchSubredditAbout() {
        if (currentSubreddit.isNotBlank() && currentSubreddit.isNotEmpty()) {
            SubredditRepositoryImpl.getSubredditAbout(currentSubreddit).subscribeBy(
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
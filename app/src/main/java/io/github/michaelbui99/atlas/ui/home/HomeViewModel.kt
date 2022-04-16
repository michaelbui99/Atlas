package io.github.michaelbui99.atlas.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.github.michaelbui99.atlas.R
import io.github.michaelbui99.atlas.model.auth.RedditAuthStore
import io.github.michaelbui99.atlas.model.domain.Subreddit
import io.github.michaelbui99.atlas.model.repositories.AuthRepositoryImpl
import io.github.michaelbui99.atlas.model.repositories.SubredditRepositoryImpl
import io.reactivex.rxjava3.kotlin.subscribeBy

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val mainSubreddits: MutableLiveData<MutableList<SubredditMainItem>> = MutableLiveData()
    val subscribedSubreddits: MutableLiveData<MutableList<Subreddit>> = MutableLiveData()
    val authStore = RedditAuthStore


    init {
        Log.i("HomeViewModel", "CREATED")
        SubredditRepositoryImpl.getDefaultSubreddits()
        subscribedSubreddits.value = mutableListOf()
        // TODO: Fetch main subreddits from Repository
        val mainSubredditsData: MutableList<SubredditMainItem> = mutableListOf(
            SubredditMainItem(
                name = application.applicationContext.resources.getString(R.string.home),
                description = application.applicationContext.resources.getString(R.string.subreddits_home_description),
                icon = R.drawable.ic_baseline_home_24
            ),
            SubredditMainItem(
                name = application.applicationContext.resources.getString(R.string.popular),
                description = application.applicationContext.resources.getString(R.string.subreddits_popular_description),
                icon = R.drawable.ic_baseline_whatshot_24
            ),
        )
        mainSubreddits.value = mainSubredditsData
        updateSubreddits()
    }

    fun updateSubreddits() {
        Log.i("HomeViewModel", "Updating subreddits")
        if (AuthRepositoryImpl.userIsLoggedIn()) {
            fetchSubscribedSubreddits()
        } else {
            fetchDefaultSubreddits()
        }
    }


    private fun fetchDefaultSubreddits() {
        SubredditRepositoryImpl.getDefaultSubreddits().subscribeBy(
            onNext = {
                Log.i("HomeViewModel", "Fetching subreddits")
                it.forEach() { subreddit ->
                    Log.i("HomeViewModel DEBUG", "Fetched: ${subreddit.displayName}")
                }
                subscribedSubreddits.postValue(it)
            },
            onError = {
                Log.e("HomeViewModel", "Failed to fetch default subreddits: ${it.message}")
            },
            onComplete = {
                Log.i("HomeViewModel", "Finished fetching default subreddits")
            }
        )
    }


    private fun fetchSubscribedSubreddits() {
        SubredditRepositoryImpl.getSubscribedSubreddits().subscribeBy(
            onNext = {
                subscribedSubreddits.postValue(it)
            },
            onError = {
                Log.e("HomeViewModel", "Failed to fetch subscribed subreddits: ${it.message}")
            }
        )
    }
}
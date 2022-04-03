package io.github.michaelbui99.atlas.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.github.michaelbui99.atlas.R
import io.github.michaelbui99.atlas.model.domain.Subreddit
import io.github.michaelbui99.atlas.model.repositories.SubredditRepositoryImpl
import io.reactivex.rxjava3.kotlin.subscribeBy

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val mainSubreddits: MutableLiveData<MutableList<SubredditMainItem>> = MutableLiveData()
    val defaultSubreddits: MutableLiveData<MutableList<Subreddit>> = MutableLiveData()


    init {
        Log.i("HomeViewModel", "CREATED")
        SubredditRepositoryImpl.getDefaultSubreddits()
        defaultSubreddits.value = mutableListOf()
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

        SubredditRepositoryImpl.getDefaultSubreddits().subscribeBy(
            onNext = {
                Log.i("HomeViewModel", "Fetching subreddits")
                it.forEach(){ subreddit ->
                    Log.i("HomeViewModel DEBUG", "Fetched: ${subreddit.displayName}")
                }
                defaultSubreddits.postValue(it)
            },
            onError = {
                Log.e("HomeViewModel", "Failed to fetch default subreddits: ${it.message}")
            },
            onComplete = {
                Log.i("HomeViewModel", "Finished fetching default subreddits")
            }
        )
    }
}
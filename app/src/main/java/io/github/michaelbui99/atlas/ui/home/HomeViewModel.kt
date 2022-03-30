package io.github.michaelbui99.atlas.ui.home

import android.app.Application
import android.content.res.Resources
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.michaelbui99.atlas.R
import io.github.michaelbui99.atlas.model.domain.Subreddit
import io.github.michaelbui99.atlas.model.repositories.SubredditRepository
import io.github.michaelbui99.atlas.model.repositories.SubredditRepositoryImpl

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = SubredditRepositoryImpl
    val mainSubreddits: MutableLiveData<MutableList<SubredditMainItem>> = MutableLiveData()
    val defaultSubreddits: MutableLiveData<MutableList<Subreddit>> = MutableLiveData()


    init {
        // TODO: Fetch main subreddits from model
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

        this.mainSubreddits.value = mainSubredditsData
    }
}
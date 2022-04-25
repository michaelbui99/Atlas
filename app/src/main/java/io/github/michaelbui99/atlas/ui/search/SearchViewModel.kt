package io.github.michaelbui99.atlas.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.michaelbui99.atlas.model.domain.Subreddit
import io.github.michaelbui99.atlas.model.repositories.RedditRepository
import io.github.michaelbui99.atlas.model.repositories.RedditRepositoryImpl
import io.reactivex.rxjava3.kotlin.subscribeBy

class SearchViewModel : ViewModel() {
    private val redditRepository: RedditRepository = RedditRepositoryImpl
    var searchQuery: String = ""
    val searchResults: MutableLiveData<MutableList<Subreddit>> = MutableLiveData(
        mutableListOf()
    )
    var resultMessage: MutableLiveData<String?> = MutableLiveData(null)
        private set

    var error: MutableLiveData<String> = MutableLiveData()
        private set


    fun search() {
        redditRepository.searchForSubreddits(searchQuery = this.searchQuery).subscribeBy(
            onNext = {
                searchResults.postValue(it)
                Log.i("SearchViewModel", it.size.toString())
                if (it.size == 0 || it == null){
                    resultMessage.postValue("No subreddits matched your query")
                }
            },
            onError = {
                error.postValue("Something might have went wrong...: ${it.message}")
            }
        )
    }

}
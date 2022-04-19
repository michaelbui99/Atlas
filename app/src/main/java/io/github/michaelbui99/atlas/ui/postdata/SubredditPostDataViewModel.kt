package io.github.michaelbui99.atlas.ui.postdata

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.michaelbui99.atlas.model.domain.SubredditPostData
import io.github.michaelbui99.atlas.model.repositories.RedditRepositoryImpl
import io.reactivex.rxjava3.kotlin.subscribeBy

class SubredditPostDataViewModel : ViewModel() {
    private lateinit var subredditName: String
    private lateinit var postId: String
    val postData: MutableLiveData<SubredditPostData> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    fun setPostInfo(subredditName: String, postId: String) {
        this.subredditName = subredditName
        this.postId = postId
        fetchPostData()
    }

    fun refreshPostData(){
        fetchPostData()
    }

    private fun fetchPostData() {
        if (subredditName.isNotBlank() && subredditName.isNotEmpty()
            && postId.isNotBlank() && postId.isNotEmpty()
        ) {
            RedditRepositoryImpl.getSubredditPostData(
                subredditName = this.subredditName,
                postId = this.postId
            ).subscribeBy(
                onNext = {
                    Log.i(
                        "SubredditPostDataViewModel",
                        "Post data has been set: ${postData.value?.title}"
                    )
                    postData.postValue(it)
                },
                onError = {
                    error.postValue("Something went wrong...: ${it.message}")
                    Log.e("SubredditPostDataViewModel", it.toString())
                },
                onComplete = {
                    Log.i("SubredditPostDataViewModel", "Finished fetching post data")
                }
            )
        }
    }
}
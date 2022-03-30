package io.github.michaelbui99.atlas.model.network

import io.github.michaelbui99.atlas.model.auth.RedditAuthenticationManager
import kotlinx.coroutines.internal.synchronized
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RedditClient() {

    fun getRedditAPI(): RedditAPI{
        return getRedditAPI()
    }

    companion object {
        private val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://reddit.com")
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()

        private var redditAPI: RedditAPI? = null

        fun getRedditAPI(): RedditAPI {
            if (redditAPI == null) {
                redditAPI = retrofit.create(RedditAPI::class.java)
            }

            return redditAPI!!
        }
    }

}
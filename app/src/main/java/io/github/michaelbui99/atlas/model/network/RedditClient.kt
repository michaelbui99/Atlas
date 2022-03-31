package io.github.michaelbui99.atlas.model.network

import com.google.gson.GsonBuilder
import io.github.michaelbui99.atlas.model.auth.RedditAuthenticationManager
import kotlinx.coroutines.internal.synchronized
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RedditClient() {

    fun redditAPI(): RedditAPI {
        return getRedditAPI()
    }

    companion object {
        private val retrofit: Retrofit
        private var redditAPI: RedditAPI? = null

        init {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val gson = GsonBuilder().setLenient().create()
            retrofit = Retrofit.Builder().baseUrl("https://reddit.com").client(client)
                .addConverterFactory(
                    GsonConverterFactory.create(gson)
                ).addCallAdapterFactory(
                    RxJava3CallAdapterFactory.create()
                )
                .build()
        }

        fun getRedditAPI(): RedditAPI {
            if (redditAPI == null) {
                redditAPI = retrofit.create(RedditAPI::class.java)
            }

            return redditAPI!!
        }
    }

}
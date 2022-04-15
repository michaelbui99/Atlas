package io.github.michaelbui99.atlas.model.network

import com.google.gson.GsonBuilder
import io.github.michaelbui99.atlas.model.auth.AccessToken
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.IllegalStateException

class RedditClient() {

    fun redditAPI(): RedditAPI {
        return getNoAuthRedditAPI()
    }


    companion object {
        private lateinit var retrofit: Retrofit
        private var noAuthRedditAPI: RedditAPI? = null
        private var authRedditAPI: RedditAPI? = null
        private var accessToken: AccessToken? = null

        fun getNoAuthRedditAPI(): RedditAPI {
            if (noAuthRedditAPI == null) {
                buildNoAuthRetrofit()
                noAuthRedditAPI = retrofit.create(RedditAPI::class.java)
            }

            return noAuthRedditAPI!!
        }

        fun getAuthRedditAPI(): RedditAPI{
            if (authRedditAPI == null){
                buildNoAuthRetrofit()
                authRedditAPI = retrofit.create(RedditAPI::class.java)
            }

            return  authRedditAPI!!
        }

        private fun buildNoAuthRetrofit() {
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

        private fun buildAuthRetrofit() {
            if (accessToken == null) {
                throw IllegalStateException("Auth flow has not been completed yet, access token is missing")
            }

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor)
                .addInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        return chain.proceed(
                            chain.request().newBuilder()
                                .addHeader("authorization", "Bearer ${accessToken!!.accessToken}")
                                .build()
                        )
                    }

                }).build()
            val gson = GsonBuilder().setLenient().create()

            retrofit = Retrofit.Builder().baseUrl("https://oauth.reddit.com").client(client)
                .addConverterFactory(
                    GsonConverterFactory.create(gson)
                ).addCallAdapterFactory(
                    RxJava3CallAdapterFactory.create()
                )
                .build()
        }
    }

}
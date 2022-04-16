package io.github.michaelbui99.atlas.model.network

import com.google.gson.GsonBuilder
import io.github.michaelbui99.atlas.model.auth.CLIENT_ID
import io.github.michaelbui99.atlas.model.auth.CLIENT_SECRET
import io.github.michaelbui99.atlas.model.util.getBase64EncodedString
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RedditAuthClient {

    companion object {
        private val retrofit: Retrofit
        private var authAPI: RedditAuthAPI? = null

        init {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder().addInterceptor(interceptor)
                .addInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        return chain.proceed(
                            chain.request().newBuilder()
                                .addHeader(
                                    "Authorization",
                                    "Basic ${getBase64EncodedString("$CLIENT_ID:$CLIENT_SECRET")}"
                                )
                                .build()
                        )
                    }
                }).build()
            val gson = GsonBuilder().setLenient().create()

            retrofit = Retrofit.Builder().baseUrl("https://www.reddit.com/api/v1/")
                .client(client)
                .addConverterFactory(
                    GsonConverterFactory.create(gson)
                ).addCallAdapterFactory(
                    RxJava3CallAdapterFactory.create()
                )
                .build()
        }


        fun getAuthAPI(): RedditAuthAPI {
            if (authAPI == null) {
                authAPI = retrofit.create(RedditAuthAPI::class.java)
            }

            return authAPI!!
        }
    }
}
package io.github.michaelbui99.atlas.model.network

import io.github.michaelbui99.atlas.model.network.responseobjects.AuthResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface RedditAuthAPI {
    @FormUrlEncoded
    @POST("api/v1/access_token")
    fun getAccessToken(
        @Field("grant_type") grantType: String,
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String
    ): Flowable<AuthResponse>
}
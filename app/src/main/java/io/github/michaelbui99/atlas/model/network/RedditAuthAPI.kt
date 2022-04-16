package io.github.michaelbui99.atlas.model.network

import io.github.michaelbui99.atlas.model.network.responseobjects.AuthResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface RedditAuthAPI {
    /**
     * Retrieves access token used to call reddit api where oauth is required
     *
     * @param grantType for default flow use "authorization_code" as grant type.
     * @param code the code retrieved after the user has granted app the necessary permissions
     * @param redirectUri the redirect uri provided during app registration
     * */
    @FormUrlEncoded
    @POST("access_token")
    fun getAccessToken(
        @Field("grant_type") grantType: String,
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String
    ): Flowable<AuthResponse>
}
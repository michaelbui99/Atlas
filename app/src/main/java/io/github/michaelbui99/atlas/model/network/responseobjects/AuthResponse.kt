package io.github.michaelbui99.atlas.model.network.responseobjects

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("expires_in")
    val expiresIn: String,
    val scope: String,
    @SerializedName("refresh_token")
    val refreshToken: String? = null
)

package io.github.michaelbui99.atlas.model.auth

import com.google.gson.annotations.SerializedName

data class AccessToken(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: String,
    val scope: String,
    val refreshToken: String? = null
)
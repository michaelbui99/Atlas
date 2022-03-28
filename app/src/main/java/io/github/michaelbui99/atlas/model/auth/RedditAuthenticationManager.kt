package io.github.michaelbui99.atlas.model.auth

interface RedditAuthenticationManager {
    fun generateAccessToken(): String
    fun refreshToken()

}
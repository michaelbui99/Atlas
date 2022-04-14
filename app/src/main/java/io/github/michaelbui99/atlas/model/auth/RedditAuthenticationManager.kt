package io.github.michaelbui99.atlas.model.auth

import android.content.SharedPreferences
import io.github.michaelbui99.atlas.model.network.RedditAuthClient

const val ACCESS_TOKEN_URL = "https://www.reddit.com/api/v1/access_token"
const val REDIRECT_URl = "https://github.com/michaelbui99/Atlas"
const val CLIENT_ID = "yVcYUCAps9f9xoO85si1_Q"
const val STATE = "UwU"

object RedditAuthenticationManager {

    private val authClient: RedditAuthClient = RedditAuthClient()

    fun generateAccessToken(code: String): String {
        throw NotImplementedError()
    }

    fun refreshToken() {

    }

    fun getExpectedState(): String {
        return STATE
    }

    fun getAuthUrl(): String {
        return "https://www.reddit.com/api/v1/authorize.compact?client_id=$CLIENT_ID" +
                "&response_type=code&state=$STATE" +
                "&redirect_uri=$REDIRECT_URl&" +
                "duration=permanent&scope=identity"
    }

}
package io.github.michaelbui99.atlas.model.auth

import android.content.SharedPreferences

const val ACCESS_TOKEN_URL = "https://www.reddit.com/api/v1/access_token"
const val REDIRECT_URl = "https://github.com/michaelbui99/Atlas"
const val CLIENT_ID = "yVcYUCAps9f9xoO85si1_Q"
const val STATE = "UwU"

object RedditAuthenticationManager {

    fun generateAccessToken(): String {
        throw NotImplementedError()
    }

    fun refreshToken() {

    }

    fun getExpectedState(): String{
        return STATE
    }

    fun getAuthUrl():String {
        return this.generateAuthUrl(clientId = CLIENT_ID, state = STATE, redirectUri = REDIRECT_URl)
    }

    private fun generateAuthUrl(clientId: String, state: String, redirectUri: String): String {
        return "https://www.reddit.com/api/v1/authorize.compact?client_id=$clientId" +
                "&response_type=code&state=$state" +
                "&redirect_uri=$redirectUri&" +
                "duration=permanent&scope=identity"
    }

}
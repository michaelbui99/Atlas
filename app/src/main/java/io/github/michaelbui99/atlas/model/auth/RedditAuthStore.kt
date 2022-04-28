package io.github.michaelbui99.atlas.model.auth

import io.github.michaelbui99.atlas.model.domain.user.RedditUser

object RedditAuthStore {
    private var accessToken: AccessToken? = null

    private var isLoggedIn: Boolean = false

    private var authCode: String? = null

    private var user: RedditUser? = null


    fun storeAuthCode(code: String) {
        this.authCode = code
    }


    fun storeAccessToken(token: AccessToken?) {
        this.accessToken = token

        if (token == null) {
            this.isLoggedIn = false
        }

        this.isLoggedIn = true
    }

    fun setRedditUser(user: RedditUser?) {
        this.user = user
    }


    fun userIsLoggedIn(): Boolean {
        return this.isLoggedIn
    }


    fun getAuthCode(): String? {
        return this.authCode
    }

    fun getAccessToken(): AccessToken? {
        return this.accessToken
    }

    fun getRedditUser(): RedditUser? {
        return this.user
    }
}
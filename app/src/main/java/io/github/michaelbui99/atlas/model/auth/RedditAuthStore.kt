package io.github.michaelbui99.atlas.model.auth

import android.util.Log
import java.lang.IllegalArgumentException

object RedditAuthStore {
    private var accessToken: AccessToken? = null

    private var isLoggedIn: Boolean = false

    private var authCode: String? = null


    fun storeAuthCode(code: String) {
        this.authCode = code
    }


    fun storeAccessToken(token: AccessToken?) {
        this.accessToken = token

        if (token == null){
            this.isLoggedIn = false
        }

        this.isLoggedIn = true
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
}
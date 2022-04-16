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


    fun storeAccessToken(token: AccessToken) {
        Log.i("AuthStore", "Storing token & Setting isLoggedIn status")
        this.isLoggedIn = true
        this.accessToken = token
        Log.i("AuthStore", "IsLoggedIn: ${this.isLoggedIn}")
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
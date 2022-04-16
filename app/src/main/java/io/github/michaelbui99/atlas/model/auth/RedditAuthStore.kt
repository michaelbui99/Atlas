package io.github.michaelbui99.atlas.model.auth

object RedditAuthStore {
    var accessToken: AccessToken? = null
        set(value) {
            if (value != null) {
                isLoggedIn = true
            }
            field = value
        }


    var isLoggedIn: Boolean = false
        set(value) {
            // Can only be logged in, if user has a access token
            if (accessToken != null && value) {
                field = value
            }

            if (!value) {
                field = value
            }
        }

    var authCode: String? = null
}
package io.github.michaelbui99.atlas.model.repositories

import io.github.michaelbui99.atlas.model.auth.AccessToken
import io.github.michaelbui99.atlas.model.auth.CLIENT_ID
import io.github.michaelbui99.atlas.model.auth.REDIRECT_URl
import io.github.michaelbui99.atlas.model.auth.STATE
import io.github.michaelbui99.atlas.model.network.RedditAuthClient
import io.reactivex.rxjava3.core.Flowable
import java.lang.IllegalStateException

object AuthRepositoryImpl : AuthRepository {
    private var accessToken: AccessToken? = null
    private var hasAccessToken: Boolean = false
    var authCode: String? = null
        set(value) {
            hasAuthCode = value != null
            field = value
        }

    private var hasAuthCode: Boolean = false

    override fun getAuthUrl(): String {
        return "https://www.reddit.com/api/v1/authorize.compact?client_id=$CLIENT_ID" +
                "&response_type=code&state=$STATE" +
                "&redirect_uri=$REDIRECT_URl&" +
                "duration=permanent&scope=identity"
    }

    override fun getAccessToken(): Flowable<AccessToken> {
        if (!this.hasAuthCode) {
            throw IllegalStateException("User has not completed auth flow")
        }

        if (accessToken != null) {
            return Flowable.just(accessToken!!)
        }

        return RedditAuthClient.getAuthAPI()
            .getAccessToken(
                grantType = "authorization",
                code = authCode!!,
                redirectUri = REDIRECT_URl
            )
            .flatMap {
                this.accessToken =
                    AccessToken(
                        it.accessToken,
                        it.tokenType,
                        it.expiresIn,
                        it.scope,
                        it.refreshToken
                    )
                hasAccessToken = true
                Flowable.just(
                    accessToken!!
                )
            }
    }

    override fun hasAccessToken(): Boolean {
        return this.hasAccessToken
    }

    override fun hasAuthCode(): Boolean {
        return this.hasAuthCode
    }
}
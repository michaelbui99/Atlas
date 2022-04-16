package io.github.michaelbui99.atlas.model.repositories

import io.github.michaelbui99.atlas.model.auth.*
import io.github.michaelbui99.atlas.model.network.RedditAuthClient
import io.reactivex.rxjava3.core.Flowable
import java.lang.IllegalStateException

object AuthRepositoryImpl : AuthRepository {
    private val authStore = RedditAuthStore

    override fun getAuthUrl(): String {
        return "https://www.reddit.com/api/v1/authorize.compact?client_id=$CLIENT_ID" +
                "&response_type=code&state=$STATE" +
                "&redirect_uri=$REDIRECT_URl&" +
                "duration=permanent&scope=identity"
    }


    override fun getAccessToken(): Flowable<AccessToken> {
        if (authStore.authCode == null) {
            throw IllegalStateException("User has not completed auth flow")
        }

        if (authStore.accessToken != null) {
            return Flowable.just(authStore.accessToken!!)
        }

        return RedditAuthClient.getAuthAPI()
            .getAccessToken(
                grantType = "authorization",
                code = authStore.authCode!!,
                redirectUri = REDIRECT_URl
            )
            .flatMap {
                this.authStore.accessToken =
                    AccessToken(
                        it.accessToken,
                        it.tokenType,
                        it.expiresIn,
                        it.scope,
                        it.refreshToken
                    )
                Flowable.just(
                    authStore.accessToken!!
                )
            }
    }


    override fun hasAccessToken(): Boolean {
        return authStore.accessToken != null
    }


    override fun hasAuthCode(): Boolean {
        return authStore.authCode != null
    }
}
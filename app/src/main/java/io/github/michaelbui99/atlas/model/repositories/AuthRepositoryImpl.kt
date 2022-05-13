package io.github.michaelbui99.atlas.model.repositories

import io.github.michaelbui99.atlas.model.auth.*
import io.github.michaelbui99.atlas.model.domain.user.Account
import io.github.michaelbui99.atlas.model.network.RedditAuthClient
import io.reactivex.rxjava3.core.Flowable
import java.lang.IllegalStateException

object AuthRepositoryImpl : AuthRepository {
    private val authStore = RedditAuthStore

    override fun getAuthUrl(): String {
        return "https://www.reddit.com/api/v1/authorize.compact?client_id=$CLIENT_ID" +
                "&response_type=code&state=$STATE" +
                "&redirect_uri=$REDIRECT_URL&" +
                "duration=permanent&scope=$SCOPES"
    }


    override fun getAccessToken(): Flowable<AccessToken> {
        if (authStore.getAuthCode() == null) {
            throw IllegalStateException("User has not completed auth flow")
        }

        if (authStore.getAccessToken() != null) {
            return Flowable.just(authStore.getAccessToken()!!)
        }

        return RedditAuthClient.getAuthAPI()
            .getAccessToken(
                grantType = "authorization_code",
                code = authStore.getAuthCode()!!,
                redirectUri = REDIRECT_URL
            )
            .flatMap {
                this.authStore.storeAccessToken(
                    AccessToken(
                        it.accessToken,
                        it.tokenType,
                        it.expiresIn,
                        it.scope,
                        it.refreshToken
                    )
                )
                Flowable.just(
                    authStore.getAccessToken()!!
                )
            }
    }


    override fun hasAccessToken(): Boolean {
        return authStore.getAccessToken() != null
    }


    override fun hasAuthCode(): Boolean {
        return authStore.getAuthCode() != null
    }


    override fun setAuthCode(authCode: String) {
        authStore.storeAuthCode(authCode)
    }


    override fun userIsLoggedIn(): Boolean {
        return authStore.userIsLoggedIn()
    }

}
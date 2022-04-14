package io.github.michaelbui99.atlas.model.repositories

import io.github.michaelbui99.atlas.model.auth.AccessToken
import io.reactivex.rxjava3.core.Flowable

interface AuthRepository {
    fun getAuthUrl(): String
    fun getAccessToken(): Flowable<AccessToken>
    fun hasAccessToken(): Boolean
    fun hasAuthCode(): Boolean
}
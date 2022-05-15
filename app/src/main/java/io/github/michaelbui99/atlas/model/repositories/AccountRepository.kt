package io.github.michaelbui99.atlas.model.repositories

import io.github.michaelbui99.atlas.model.domain.settings.AppSettings
import io.github.michaelbui99.atlas.model.domain.user.Account
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

interface AccountRepository {
    fun getAccountByRedditName(name: String): Account?
    fun addAccount(account: Account)
    fun ensureUserHasLocalAccount(redditName: String)
    suspend fun updateAccount(account: Account)
}
package io.github.michaelbui99.atlas.model.repositories

import io.github.michaelbui99.atlas.model.domain.settings.AppSettings
import io.github.michaelbui99.atlas.model.domain.user.Account
import io.github.michaelbui99.atlas.model.persistence.AccountDatabase
import io.github.michaelbui99.atlas.model.util.ApplicationContextProvider
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

object AccountRepositoryImpl : AccountRepository {
    private val accountDAO = AccountDatabase.getInstance(
        ApplicationContextProvider.getInstance().getContext().applicationContext
    )!!.accountDao()

    override fun getAccountByRedditName(name: String): Maybe<Account> {
        return accountDAO.getAccountByRedditName(name);
    }

    override fun setAccount(account: Account): Completable {
        return accountDAO.insert(account = account)
    }
}
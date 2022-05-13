package io.github.michaelbui99.atlas.model.repositories

import io.github.michaelbui99.atlas.model.domain.settings.AppSettings
import io.github.michaelbui99.atlas.model.domain.user.Account
import io.github.michaelbui99.atlas.model.persistence.AccountDatabase
import io.github.michaelbui99.atlas.model.util.ApplicationContextProvider

class AccountRepositoryImpl : AccountRepository {
    private val accountDAO = AccountDatabase.getInstance(
        ApplicationContextProvider.getInstance().getContext().applicationContext
    ).accountDao()

    override fun getAccountByRedditName(name: String): Account? {
        return accountDAO.getAccountByRedditName(name);
    }

    override fun addAccount(account: Account) {
        return accountDAO.insert(account = account)
    }

    override fun ensureUserHasLocalAccount(redditName: String) {
        val account =
            getAccountByRedditName(redditName)
        if (account == null) {
            addAccount(
                Account(redditName = redditName, appSettings = AppSettings())
            )
        }
    }

    override fun updateAccount(account: Account) {
        accountDAO.update(account)
    }

    companion object {
        private var instance: AccountRepository? = null

        @Synchronized
        fun getInstance(): AccountRepository {
            if (instance == null) {
                instance = AccountRepositoryImpl()
            }

            return instance!!
        }
    }
}
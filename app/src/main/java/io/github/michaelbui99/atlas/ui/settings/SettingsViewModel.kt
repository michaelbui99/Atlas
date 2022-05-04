package io.github.michaelbui99.atlas.ui.settings

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import io.github.michaelbui99.atlas.model.domain.user.Account
import io.github.michaelbui99.atlas.model.domain.user.RedditUser
import io.github.michaelbui99.atlas.model.repositories.*
import io.reactivex.rxjava3.kotlin.subscribeBy

class SettingsViewModel(val app: Application) : AndroidViewModel(app) {
    private val authRepository: AuthRepository = AuthRepositoryImpl
    private val accountRepository: AccountRepository = AccountRepositoryImpl.getInstance()
    private val redditRepository: RedditRepository = RedditRepositoryImpl

    val useDarkMode: MutableLiveData<Boolean> = MutableLiveData()

    fun ensureCorrectSettings() {

        if (authRepository.userIsLoggedIn()) {
            useAccountSettings()
        } else {
            useLocalDeviceSettings()
        }
    }

    private fun useAccountSettings() {
        redditRepository.getMe().subscribeBy {
            val loggedInUserRedditName = it.displayName
            accountRepository.getAccountByRedditName(loggedInUserRedditName).subscribeBy(
                onSuccess = { account ->
                    useDarkMode.postValue(account.appSettings?.useDarkMode)
                },
            )
        }
    }

    private fun useLocalDeviceSettings() {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplication())
        val useDarkModeSettingValue = sharedPrefs.getBoolean("darkMode", false)

        sharedPrefs.registerOnSharedPreferenceChangeListener { _, p1 ->
            if (p1 == "darkMode") {
                val useDarkModeVal = sharedPrefs.getBoolean("darkMode", false)
                useDarkMode.value = useDarkModeVal
            }
        }
        useDarkMode.value = useDarkModeSettingValue
    }
}
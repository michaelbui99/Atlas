package io.github.michaelbui99.atlas.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import io.github.michaelbui99.atlas.model.repositories.*
import io.github.michaelbui99.atlas.model.util.putBoolean
import io.reactivex.rxjava3.kotlin.subscribeBy

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val authRepository: AuthRepository = AuthRepositoryImpl
    private val redditRepository: RedditRepository = RedditRepositoryImpl
    private val accountRepository: AccountRepository = AccountRepositoryImpl.getInstance()
    private val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(app)
    val useDarkMode: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        sharedPrefs.registerOnSharedPreferenceChangeListener { _, p1 ->
            if (p1 == "darkMode") {
                val useDarkModeVal = sharedPrefs.getBoolean("darkMode", false)
                useDarkMode.value = useDarkModeVal
            }
        }
    }

    fun ensureCorrectSettings() {
        if (authRepository.userIsLoggedIn()) {
            useAccountSettings()
        } else {
            useLocalDeviceSettings()
        }
    }

    private fun useAccountSettings() {
        redditRepository.getMe().subscribeBy(
            onNext = {
                val loggedInUserRedditName = it.displayName
                val localAccount = accountRepository.getAccountByRedditName(loggedInUserRedditName)
                if (localAccount != null) {
                    useDarkMode.postValue(localAccount.appSettings?.useDarkMode)
                    putBoolean(
                        this.getApplication(),
                        "darkMode",
                        localAccount.appSettings!!.useDarkMode
                    )
                }
            },
            onError = {
                Log.e("MainViewModel", it.toString())
            })
    }

    private fun useLocalDeviceSettings() {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplication())
        val useDarkModeSettingValue = sharedPrefs.getBoolean("darkMode", false)

        useDarkMode.value = useDarkModeSettingValue
    }
}
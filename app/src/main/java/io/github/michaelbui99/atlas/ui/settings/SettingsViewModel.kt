package io.github.michaelbui99.atlas.ui.settings

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import io.github.michaelbui99.atlas.model.domain.user.Account
import io.github.michaelbui99.atlas.model.repositories.*
import io.github.michaelbui99.atlas.model.util.putBoolean
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.launch
import java.lang.IllegalStateException

class SettingsViewModel(val app: Application) : AndroidViewModel(app) {
    private val authRepository: AuthRepository = AuthRepositoryImpl
    private val accountRepository: AccountRepository = AccountRepositoryImpl.getInstance()
    private val redditRepository: RedditRepository = RedditRepositoryImpl
    private val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(app)
    private var localAccount: Account? = null

    val useDarkMode: MutableLiveData<Boolean> = MutableLiveData()

    init {
        sharedPrefs.registerOnSharedPreferenceChangeListener { _, p1 ->
            if (p1 == "darkMode") {
                val useDarkModeVal = sharedPrefs.getBoolean("darkMode", false)

                if (localAccount != null) {
                    localAccount!!.appSettings?.useDarkMode = useDarkModeVal
                    updateAccountSettings()
                    ensureCorrectSettings()
                }

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

    private fun updateAccountSettings() {
        if (localAccount == null) {
            throw IllegalStateException("No account")
        }
        ensureCorrectSettings()
        viewModelScope.launch {
            accountRepository.updateAccount(account = localAccount!!)
        }
    }

    private fun useAccountSettings() {
        Log.i("SettingsViewModel", "Using Account Settings")
        redditRepository.getMe().subscribeBy(
            onNext = {
                val loggedInUserRedditName = it.displayName
                localAccount = accountRepository.getAccountByRedditName(loggedInUserRedditName)
                if (localAccount != null) {
                    useDarkMode.postValue(localAccount?.appSettings?.useDarkMode)
                    putBoolean(
                        app.applicationContext,
                        "darkMode",
                        localAccount!!.appSettings!!.useDarkMode
                    )
                }

            },
            onError = {
                Log.e("SettingsViewModel", it.toString())
            })
    }

    private fun useLocalDeviceSettings() {
        val useDarkModeSettingValue = sharedPrefs.getBoolean("darkMode", false)
//
//        sharedPrefs.registerOnSharedPreferenceChangeListener { _, p1 ->
//            if (p1 == "darkMode") {
//                val useDarkModeVal = sharedPrefs.getBoolean("darkMode", false)
//                useDarkMode.value = useDarkModeVal
//            }
//        }
        useDarkMode.value = useDarkModeSettingValue
        Log.i("SettingsViewModel", "Using Local Settings")
    }
}
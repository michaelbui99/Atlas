package io.github.michaelbui99.atlas.model.domain.settings

import android.annotation.SuppressLint
import android.content.Context
import io.github.michaelbui99.atlas.model.domain.user.Account
import io.github.michaelbui99.atlas.model.util.ApplicationContextProvider
import io.github.michaelbui99.atlas.model.util.putBoolean
import java.lang.IllegalStateException

class SettingsManager private constructor() {
    private var account: Account? = null
    private var context: Context =
        ApplicationContextProvider.getInstance().getContext().applicationContext

    fun setAccount(account: Account) {
        this.account = account
        syncAccountSettingsWithDeviceSettings()
    }

    fun accountSettingsChanged() {
        syncAccountSettingsWithDeviceSettings()
    }

    private fun syncAccountSettingsWithDeviceSettings() {
        if (account == null) {
            throw IllegalStateException("No account has been set. No account settings available to sync with")
        }

        val settings = account!!.appSettings
        putBoolean(this.context.applicationContext, "darkMode", settings!!.useDarkMode)
        putBoolean(this.context, "syncSettings", settings.syncSettingsAcrossDevices)
    }


    companion object {
        // setContext sets the context to application context, so this suppress should be fine.
        @SuppressLint("StaticFieldLeak")
        private var instance: SettingsManager? = null

        @Synchronized
        fun getInstance(): SettingsManager {
            if (instance == null) {
                instance = SettingsManager()
            }

            return instance!!
        }
    }
}
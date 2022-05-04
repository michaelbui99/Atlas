package io.github.michaelbui99.atlas.ui.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.github.michaelbui99.atlas.model.repositories.AccountRepository
import io.github.michaelbui99.atlas.model.repositories.AccountRepositoryImpl
import io.github.michaelbui99.atlas.model.repositories.AuthRepository
import io.github.michaelbui99.atlas.model.repositories.AuthRepositoryImpl

class SettingsViewModel(app: Application) : AndroidViewModel(app) {
    private val authRepository: AuthRepository = AuthRepositoryImpl
    private val accountRepository: AccountRepository = AccountRepositoryImpl.getInstance()
}
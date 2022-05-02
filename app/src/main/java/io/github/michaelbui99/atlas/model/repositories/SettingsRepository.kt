package io.github.michaelbui99.atlas.model.repositories

import io.github.michaelbui99.atlas.model.domain.settings.AppSettings

interface SettingsRepository {
    fun getSettings(): AppSettings
}
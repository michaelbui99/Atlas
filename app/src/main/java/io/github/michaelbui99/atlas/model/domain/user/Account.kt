package io.github.michaelbui99.atlas.model.domain.user

import io.github.michaelbui99.atlas.model.domain.settings.AppSettings

data class Account(
    var user: RedditUser?,
    var appSettings: AppSettings?
) {
}
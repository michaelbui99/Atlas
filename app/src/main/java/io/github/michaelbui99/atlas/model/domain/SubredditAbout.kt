package io.github.michaelbui99.atlas.model.domain

import com.google.gson.annotations.SerializedName

data class SubredditAbout(
    val displayNamePrefixed: String,
    val description: String,
    val iconImage: String?,
    val subscribers: Long,
    val activeAccounts: Long
)
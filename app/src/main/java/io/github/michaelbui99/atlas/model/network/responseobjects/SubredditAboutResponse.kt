package io.github.michaelbui99.atlas.model.network.responseobjects

import com.google.gson.annotations.SerializedName

data class SubredditAboutResponse(val kind: String, val data: SubredditAboutData)

data class SubredditAboutData(
    @SerializedName("display_name_prefixed")
    val displayNamePrefixed: String,
    @SerializedName("public_description")
    val description: String,
    @SerializedName("icon_img")
    val iconImage: String?,
    val subscribers: Long,
    @SerializedName("accounts_active")
    val activeAccounts: Long
)

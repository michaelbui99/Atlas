package io.github.michaelbui99.atlas.model.network.responseobjects

import com.google.gson.annotations.SerializedName

/**
 * Response object wrapping the data received, when fetching subreddit's about data
 * e.g. GET https://www.reddit.com/r/art/about.json
 * */
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

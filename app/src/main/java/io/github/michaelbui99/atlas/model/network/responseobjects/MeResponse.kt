package io.github.michaelbui99.atlas.model.network.responseobjects

import com.google.gson.annotations.SerializedName

data class MeResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("icon_img")
    val iconImageUrl: String?,
    @SerializedName("created_utc")
    val createdUtc: Long,
    @SerializedName("total_karma")
    val totalKarma: Long
) {
}
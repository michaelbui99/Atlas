package io.github.michaelbui99.atlas.model.domain.user

import java.time.LocalDateTime

data class RedditUser(
    val displayName: String,
    val iconUrl: String? = null,
    val karmaCount: Long,
    val createdUtc: LocalDateTime
) {
}
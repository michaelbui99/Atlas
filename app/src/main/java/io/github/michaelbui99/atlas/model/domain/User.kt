package io.github.michaelbui99.atlas.model.domain

import java.time.LocalDateTime

data class User(val displayName: String, val iconUrl: String? = null, val karmaCount: Long, val createdUtc:LocalDateTime) {
}
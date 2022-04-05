package io.github.michaelbui99.atlas.model.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

fun convertUnixToLocalDate(unix: Long): LocalDateTime {
    return Instant.ofEpochSecond(unix).atOffset(ZoneOffset.UTC).toLocalDateTime()
}
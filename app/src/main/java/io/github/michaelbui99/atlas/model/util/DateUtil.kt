package io.github.michaelbui99.atlas.model.util

import java.time.*
import kotlin.math.abs

fun convertUnixToLocalDateTime(unix: Long): LocalDateTime {
    return Instant.ofEpochSecond(unix).atOffset(ZoneOffset.UTC).toLocalDateTime()
}

fun getMonthsBetweenLocalDateTimes(date1: LocalDateTime, date2: LocalDateTime): Int {
    val period = Period.between(date1.toLocalDate(), date2.toLocalDate())
    return (abs(period.years)*12) + abs(period.months)
}

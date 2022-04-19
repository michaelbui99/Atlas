package io.github.michaelbui99.atlas.model.cache

import java.time.LocalDateTime

data class CacheEntry<T>(
    var entry: T? = null,
    var expireAt: LocalDateTime? = null
) {

}
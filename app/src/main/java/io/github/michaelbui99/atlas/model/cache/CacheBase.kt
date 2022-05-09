package io.github.michaelbui99.atlas.model.cache

abstract class CacheBase<T> {
    internal val cacheEntries: HashMap<String, CacheEntry<T>> = hashMapOf()

    abstract fun addCacheEntry(entry: T, minutesToExpiration: Long)
    abstract fun getCacheEntries(): MutableList<T>


}
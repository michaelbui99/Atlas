package io.github.michaelbui99.atlas.model.cache

import io.github.michaelbui99.atlas.model.domain.Subreddit
import java.time.LocalDateTime
import java.util.*

/**
 * Simple cache for caching subreddits. Uses a hashmap for quick look up times,
 * when updating cache entries.
 * Display name of the subreddit is used as key for Cache Entries
 * */
object SubredditsCache {
    private val cacheEntries: HashMap<String, CacheEntry<Subreddit>> = hashMapOf()


    /**
     * Adds a new Cache Entry. If a the cache already contains a subreddit with same name,
     * then it's time to expire will be updated to: now + minutesToExpiration
     *
     * @param subreddit the Subreddit with are to be cached
     * @param minutesToExpiration How many minutes before the cache entry should expire
     * */
    fun addCacheEntry(subreddit: Subreddit, minutesToExpiration: Long) {
        if (cacheContainsEntry(subreddit)) {
            cacheEntries[subreddit.displayName]!!.expireAt =
                LocalDateTime.now().plusMinutes(minutesToExpiration)
            return
        }

        val newEntry =
            CacheEntry<Subreddit>(subreddit, LocalDateTime.now().plusMinutes(minutesToExpiration))
        cacheEntries[subreddit.displayName] = newEntry
    }


    /**
     * Returns all non-expired cache entries
     *
     * @return List of all non-expired cache entries
     * */
    fun getCacheEntries(): MutableList<Subreddit> {
        ensureCacheValidity()
        val entries = mutableListOf<Subreddit>()

        cacheEntries.forEach {
            entries.add(it.value.entry!!)
        }

        return entries
    }


    fun clearCache() {
        cacheEntries.clear()
    }

    /**
     * Removes all Cache Entries that have expired
     * */
    private fun ensureCacheValidity() {
        cacheEntries.forEach {
            if (LocalDateTime.now().isAfter(it.value.expireAt)) {
                cacheEntries.remove(it.key)
            }
        }
    }

    private fun cacheContainsEntry(subreddit: Subreddit): Boolean {
        return cacheEntries.containsKey(subreddit.displayName)
    }
}
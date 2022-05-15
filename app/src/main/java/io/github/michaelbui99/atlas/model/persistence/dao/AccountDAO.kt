package io.github.michaelbui99.atlas.model.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.github.michaelbui99.atlas.model.domain.user.Account

@Dao
interface AccountDAO {

    @Insert
    fun insert(account: Account)

    @Update
    suspend fun update(account: Account)

    @Query("SELECT * FROM account where redditName = :name")
    fun getAccountByRedditName(name: String): Account?
}
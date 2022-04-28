package io.github.michaelbui99.atlas.model.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.github.michaelbui99.atlas.model.domain.user.Account
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

@Dao
interface AccountDAO {

    @Insert
    fun insert(account: Account): Completable

    @Update
    fun update(account: Account): Completable

    @Query("SELECT * FROM account where redditName = :name")
    fun getAccountByRedditName(name: String): Maybe<Account>
}
package io.github.michaelbui99.atlas.model.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.michaelbui99.atlas.model.domain.user.Account
import io.github.michaelbui99.atlas.model.persistence.dao.AccountDAO

@Database(entities = [Account::class], version = 1)
@TypeConverters(RoomTypeConverters::class)
abstract class AccountDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDAO

    companion object {
        private var instance: AccountDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AccountDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AccountDatabase::class.java,
                    "account_db"
                ).fallbackToDestructiveMigration().build()
            }

            return instance as AccountDatabase
        }
    }
}
package io.github.michaelbui99.atlas.model.domain.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import io.github.michaelbui99.atlas.model.domain.settings.AppSettings

@Entity
@TypeConverters(TypeConverters::class)
data class Account(
    @PrimaryKey(autoGenerate = false)
    var redditName: String?,

    @ColumnInfo(name = "app_settings")
    var appSettings: AppSettings?
) {
}
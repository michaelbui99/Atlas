package io.github.michaelbui99.atlas.model.domain.user

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.michaelbui99.atlas.model.domain.settings.AppSettings

@Entity
data class Account(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    var redditName: String,

    @ColumnInfo(name = "app_settings")
    var appSettings: AppSettings?
) {
}
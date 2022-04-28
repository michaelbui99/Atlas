package io.github.michaelbui99.atlas.model.persistence

import androidx.room.TypeConverter
import com.google.gson.Gson
import io.github.michaelbui99.atlas.model.domain.settings.AppSettings
import io.github.michaelbui99.atlas.model.domain.user.Account
import io.github.michaelbui99.atlas.model.domain.user.RedditUser

class TypeConverters {

    @TypeConverter
    fun appSettingsToJsonString(appSettings: AppSettings): String {
        return Gson().toJson(appSettings)
    }

    @TypeConverter
    fun appSettingsFromJsonString(jsonString: String): AppSettings {
        return Gson().fromJson(jsonString, AppSettings::class.java)
    }
}
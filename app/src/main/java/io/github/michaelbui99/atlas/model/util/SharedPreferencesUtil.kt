package io.github.michaelbui99.atlas.model.util

import android.content.Context
import android.content.Context.MODE_PRIVATE

fun putBoolean(context: Context, key: String, value: Boolean = false) {
    val sharedPrefs = context.getSharedPreferences("settings", MODE_PRIVATE);
    sharedPrefs.edit().putBoolean(key, value).apply()
}

fun putString(context: Context, key: String, value: String = "") {
    val sharedPrefs = context.getSharedPreferences("settings", MODE_PRIVATE);
    sharedPrefs.edit().putString(key, value).apply()
}
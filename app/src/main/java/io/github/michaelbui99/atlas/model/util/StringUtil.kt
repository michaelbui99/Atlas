package io.github.michaelbui99.atlas.model.util

import android.util.Base64

fun getBase64EncodedString(string: String): String{
    return Base64.encodeToString(string.toByteArray(), Base64.NO_WRAP)
}
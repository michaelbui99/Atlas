package io.github.michaelbui99.atlas.model.http.responseobjects

data class ResponseDataWrapper<T>(val kind: String, val data: T)
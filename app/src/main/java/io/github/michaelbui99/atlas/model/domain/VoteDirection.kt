package io.github.michaelbui99.atlas.model.domain

enum class VoteDirection(val dir: Int) {
    UP_VOTE(1),
    UN_VOTE(0),
    DOWN_VOTE(-1)
}
package com.gmkornilov.shikimori.domain.models.common

enum class AnimeOrder {
    ID,
    RANKED,
    KIND,
    POPULARITY,
    NAME,
    AIRED_ON,
    EPISODES,
    STATUS,
    RANDOM;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}
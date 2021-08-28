package com.gmkornilov.shikimori.data.models.common

import com.gmkornilov.shikimori.domain.models.common.AnimeOrder as DomainAnimeOrder

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

fun DomainAnimeOrder.toDataAnimeOrder(): AnimeOrder {
    return AnimeOrder.valueOf(this.toString())
}
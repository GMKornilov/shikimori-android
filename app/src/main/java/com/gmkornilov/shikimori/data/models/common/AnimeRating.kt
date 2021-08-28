package com.gmkornilov.shikimori.data.models.common

import com.gmkornilov.shikimori.domain.models.common.AnimeRating as DomainAnimeRating

enum class AnimeRating {
    NONE,
    G,
    PG,
    PG_13,
    R,
    R_PLUS,
    RX; // RxJava, KEKW

    override fun toString(): String {
        return super.toString().lowercase()
    }
}


fun AnimeRating.toDomainAnimeRating(): DomainAnimeRating {
    return DomainAnimeRating.valueOf(this.toString().uppercase())
}

fun DomainAnimeRating.toDataAnimeRating(): AnimeRating {
    return AnimeRating.valueOf(this.toString())
}
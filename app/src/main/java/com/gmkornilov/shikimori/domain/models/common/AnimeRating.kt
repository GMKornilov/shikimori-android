package com.gmkornilov.shikimori.domain.models.common

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
package com.gmkornilov.shikimori.data.models.common

import com.gmkornilov.shikimori.domain.models.common.AnimeRating as DomainAnimeRating

/**
 * Age rating of anime
 */
enum class AnimeRating {
    /**
     * No rating
     */
    NONE,

    /**
     * G - ll ages
     */
    G,

    /**
     * PG - children
     */
    PG,

    /**
     * PG-13 - teens 13 or older
     */
    PG_13,

    /**
     * R - 17+ recommended (violence & profanity
     */
    R,

    /**
     * R+ - Mild Nudity (may also contain violence & profanity)
     */
    R_PLUS,

    /**
     * Rx - extreme sexual content/nudity (phew)
     */
    RX; // RxJava, KEKW

    override fun toString(): String {
        return super.toString().lowercase()
    }
}


/**
 * Convert data model of age rating to domain model
 */
fun AnimeRating.toDomainAnimeRating(): DomainAnimeRating {
    return DomainAnimeRating.valueOf(this.toString().uppercase())
}

/**
 * Convert domain model of age rating to data model
 */
fun DomainAnimeRating.toDataAnimeRating(): AnimeRating {
    return AnimeRating.valueOf(this.toString())
}
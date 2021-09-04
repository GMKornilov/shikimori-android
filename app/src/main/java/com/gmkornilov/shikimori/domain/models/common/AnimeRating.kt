package com.gmkornilov.shikimori.domain.models.common

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
    RX;
}
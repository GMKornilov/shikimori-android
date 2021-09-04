package com.gmkornilov.shikimori.domain.models.common

/**
 * Kind of anime
 */
enum class AnimeKind {
    /**
     * TV-series
     */
    TV,

    /**
     * Single movie
     */
    MOVIE,

    /**
     * OVA (Original Video Animation)
     */
    OVA,

    /**
     * ONA (Original Net Animation)
     */
    ONA,

    /**
     * Special series
     */
    SPECIAL,

    /**
     * Music video
     */
    MUSIC,

    /**
     * TV-series with less than 13 series)
     */
    TV_13,

    /**
     * TV-series with between 13 and 24 series
     */
    TV_24,

    /**
     * TV-series with 24+ series
     */
    TV_48,
}
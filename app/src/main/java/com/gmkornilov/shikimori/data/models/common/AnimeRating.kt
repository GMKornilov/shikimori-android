package com.gmkornilov.shikimori.data.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.gmkornilov.shikimori.domain.models.common.AnimeRating as DomainAnimeRating

/**
 * Age rating of anime
 */
@Serializable
enum class AnimeRating {
    /**
     * No rating
     */
    @SerialName("none") NONE,

    /**
     * G - ll ages
     */
    @SerialName("g") G,

    /**
     * PG - children
     */
    @SerialName("pg") PG,

    /**
     * PG-13 - teens 13 or older
     */
    @SerialName("pg_13") PG_13,

    /**
     * R - 17+ recommended (violence & profanity
     */
    @SerialName("r") R,

    /**
     * R+ - Mild Nudity (may also contain violence & profanity)
     */
    @SerialName("r_plus") R_PLUS,

    /**
     * Rx - extreme sexual content/nudity (phew)
     */
    @SerialName("rx") RX; // RxJava, KEKW

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
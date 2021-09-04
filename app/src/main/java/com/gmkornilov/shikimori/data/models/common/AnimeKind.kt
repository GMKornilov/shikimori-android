package com.gmkornilov.shikimori.data.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.gmkornilov.shikimori.domain.models.common.AnimeKind as DomainAnimeKind

/**
 * Kind of anime
 */
@Serializable
enum class AnimeKind {
    /**
     * TV-series
     */
    @SerialName("tv") TV,

    /**
     * Single movie
     */
    @SerialName("movie") MOVIE,

    /**
     * OVA (Original Video Animation)
     */
    @SerialName("ova") OVA,

    /**
     * ONA (Original Net Animation)
     */
    @SerialName("ona") ONA,

    /**
     * Special series
     */
    @SerialName("special") SPECIAL,

    /**
     * Music video
     */
    @SerialName("music") MUSIC,

    /**
     * TV-series with less than 13 series)
     */
    @SerialName("tv_13") TV_13,

    /**
     * TV-series with between 13 and 24 series
     */
    @SerialName("tv_24") TV_24,

    /**
     * TV-series with 24+ series
     */
    @SerialName("tv_48") TV_48;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}

fun AnimeKind.toDomainAnimeKind(): DomainAnimeKind {
    return DomainAnimeKind.valueOf(this.toString().uppercase())
}

fun DomainAnimeKind.toDataAnimeKind(): AnimeKind {
    return AnimeKind.valueOf(this.toString())
}
package com.gmkornilov.shikimori.data.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.gmkornilov.shikimori.domain.models.common.AnimeKind as DomainAnimeKind

@Serializable
enum class AnimeKind {
    @SerialName("tv") TV,
    @SerialName("movie") MOVIE,
    @SerialName("ova") OVA,
    @SerialName("ona") ONA,
    @SerialName("special") SPECIAL,
    @SerialName("music") MUSIC,
    @SerialName("tv_13") TV_13,
    @SerialName("tv_24") TV_24,
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
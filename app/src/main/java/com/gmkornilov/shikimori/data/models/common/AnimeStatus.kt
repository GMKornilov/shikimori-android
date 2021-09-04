package com.gmkornilov.shikimori.data.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.gmkornilov.shikimori.domain.models.common.AnimeStatus as DomainAnimeStatus

/**
 * Status of anime
 */
@Serializable
enum class AnimeStatus {
    /**
     * Anime is announced at the moment
     */
    @SerialName("anons") ANONS,

    /**
     * Anime is ongoing now(partly released)
     */
    @SerialName("ongoing") ONGOING,

    /**
     * Anime is fully released
     */
    @SerialName("released") RELEASED;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}

/**
 * Convert data model of anime status to domain model
 */
fun AnimeStatus.toDomainAnimeStatus(): DomainAnimeStatus {
    return DomainAnimeStatus.valueOf(this.toString().uppercase())
}

/**
 * Convert domain model of anime status to data model
 */
fun DomainAnimeStatus.toDataAnimeStatus(): AnimeStatus {
    return AnimeStatus.valueOf(this.toString())
}
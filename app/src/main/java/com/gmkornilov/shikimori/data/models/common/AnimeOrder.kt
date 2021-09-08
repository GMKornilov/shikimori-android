package com.gmkornilov.shikimori.data.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.gmkornilov.shikimori.domain.models.common.AnimeOrder as DomainAnimeOrder

/**
 * Order of how searched animes are given
 */
@Serializable
enum class AnimeOrder {
    /**
     * Ordered by id
     */
    @SerialName("id") ID,

    /**
     * Ordered by rank
     */
    @SerialName("ranked") RANKED,

    /**
     * Ordered by kind
     */
    @SerialName("kind") KIND,

    /**
     * Ordered by popularity
     */
    @SerialName("popularity") POPULARITY,

    /**
     * Ordered in alphabetical order
     */
    @SerialName("name") NAME,

    /**
     * Ordered by released date
     */
    @SerialName("aired_on") AIRED_ON,

    /**
     * Ordered by amount of episodes
     */
    @SerialName("episodes") EPISODES,

    /**
     * Ordered by status
     */
    @SerialName("status") STATUS,

    /**
     * Random order
     */
    @SerialName("random") RANDOM;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}

/**
 * Convert domain model of order to data model
 */
fun DomainAnimeOrder.toDataAnimeOrder(): AnimeOrder {
    return AnimeOrder.valueOf(this.toString())
}
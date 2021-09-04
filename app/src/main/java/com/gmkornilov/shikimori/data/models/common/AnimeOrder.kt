package com.gmkornilov.shikimori.data.models.common

import com.gmkornilov.shikimori.domain.models.common.AnimeOrder as DomainAnimeOrder

/**
 * Order of how searched animes are given
 */
enum class AnimeOrder {
    /**
     * Ordered by id
     */
    ID,

    /**
     * Ordered by rank
     */
    RANKED,

    /**
     * Ordered by kind
     */
    KIND,

    /**
     * Ordered by popularity
     */
    POPULARITY,

    /**
     * Ordered in alphabetical order
     */
    NAME,

    /**
     * Ordered by released date
     */
    AIRED_ON,

    /**
     * Ordered by amount of episodes
     */
    EPISODES,

    /**
     * Ordered by status
     */
    STATUS,

    /**
     * Random order
     */
    RANDOM;

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
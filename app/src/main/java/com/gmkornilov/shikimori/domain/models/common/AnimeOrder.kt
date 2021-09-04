package com.gmkornilov.shikimori.domain.models.common

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
}
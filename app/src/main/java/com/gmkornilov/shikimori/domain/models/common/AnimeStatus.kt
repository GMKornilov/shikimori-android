package com.gmkornilov.shikimori.domain.models.common

/**
 * Status of anime
 */
enum class AnimeStatus {
    /**
     * Anime is announced at the moment
     */
    ANONS,

    /**
     * Anime is ongoing now(partly released)
     */
    ONGOING,

    /**
     * Anime is fully released
     */
    RELEASED,
}
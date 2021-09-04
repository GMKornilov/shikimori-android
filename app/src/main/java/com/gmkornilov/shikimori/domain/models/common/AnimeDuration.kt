package com.gmkornilov.shikimori.domain.models.common

/**
 * Enum of average series duration in given anime
 */
enum class AnimeDuration {
    /**
     * Stands for short series (shorter than 10 minutes)
     */
    S,

    /**
     * Stands for medium series (between 10 and 30 minutes)
     */
    D,

    /**
     * Long series (longer than 30 episodes)
     */
    F,
}

package com.gmkornilov.shikimori.data.models.common

import kotlinx.serialization.SerialName
import com.gmkornilov.shikimori.domain.models.common.AnimeDuration as DomainAnimeDuration

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

/**
 * Convert data model of anime duration to domain model
 */
fun AnimeDuration.toDomainAnimeDuration(): DomainAnimeDuration {
    return DomainAnimeDuration.valueOf(this.toString())
}

/**
 * Convert domain model of anime duration to data model
 */
fun DomainAnimeDuration.toDataAnimeDuration(): AnimeDuration {
    return AnimeDuration.valueOf(this.toString())
}
package com.gmkornilov.shikimori.domain.models.common

/**
 * Information about genre
 */
data class Genre(
    /**
     * Genre id
     */
    val id: Long,

    /**
     * Genre's english name
     */
    val name: String,

    /**
     * Genre's russian name
     */
    val russian: String,

    /**
     * Genre's kind (can have "anime" or "manga")
     */
    val kind: String,
)

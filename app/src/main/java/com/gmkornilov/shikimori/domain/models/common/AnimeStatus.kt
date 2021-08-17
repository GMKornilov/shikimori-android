package com.gmkornilov.shikimori.domain.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class AnimeStatus {
    @SerialName("anons") ANONS,
    @SerialName("ongoing") ONGOING,
    @SerialName("released") RELEASED;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}
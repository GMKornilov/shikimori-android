package com.gmkornilov.shikimori.domain.models.common

import kotlinx.serialization.Serializable

@Serializable
data class NamedStat(
    val name: Int,
    val value: Int,
)

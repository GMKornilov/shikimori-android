package com.gmkornilov.shikimori.data.models.common

import com.gmkornilov.shikimori.domain.models.common.AnimeDuration as DomainAnimeDuration

enum class AnimeDuration {
    S,
    D,
    F,
}

fun AnimeDuration.toDomainAnimeDuration(): DomainAnimeDuration {
    return DomainAnimeDuration.valueOf(this.toString())
}

fun DomainAnimeDuration.toDataAnimeDuration(): AnimeDuration {
    return AnimeDuration.valueOf(this.toString())
}
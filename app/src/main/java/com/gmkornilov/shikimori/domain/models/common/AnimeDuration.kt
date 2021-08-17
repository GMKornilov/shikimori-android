package com.gmkornilov.shikimori.domain.models.common

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.internal.*

enum class AnimeDuration {
    S,
    D,
    F,
}

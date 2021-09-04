package com.gmkornilov.shikimori.data.models.serializers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Serializer for date objects
 */
@ExperimentalSerializationApi
@Serializer(forClass = Date::class)
object DateSerializer : KSerializer<Date?> {
    private val df: DateFormat = SimpleDateFormat("yyyy-MM-DD", Locale.ENGLISH)

    override fun serialize(encoder: Encoder, value: Date?) {
        if (value == null) {
            encoder.encodeString("null")
        } else {
            encoder.encodeString(df.format(value))
        }
    }

    override fun deserialize(decoder: Decoder): Date? {
        return df.parse(decoder.decodeString())
    }
}
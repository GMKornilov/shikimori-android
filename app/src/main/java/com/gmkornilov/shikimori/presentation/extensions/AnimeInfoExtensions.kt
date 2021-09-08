package com.gmkornilov.shikimori.presentation.extensions

import android.content.Context
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.data.models.common.toDataAnimeRating
import com.gmkornilov.shikimori.domain.models.common.AnimeInfo
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.gmkornilov.shikimori.presentation.components.description.DescriptionComponent
import com.gmkornilov.shikimori.presentation.components.keyvalue.KeyValue
import com.gmkornilov.shikimori.presentation.components.sectionheader.SectionHeaderComponent
import com.gmkornilov.shikimori.presentation.items.animepreview.toPresentationAnimeKind


fun AnimeInfo.toAnimePageItems(context: Context): List<BaseComponent> {
    val result = mutableListOf<BaseComponent>()

    if (this.description != null) {
        result.add(
            SectionHeaderComponent(
                context.getString(R.string.description)
            )
        )
        result.add(DescriptionComponent(this.description))
    }

    val informations = this.toInformation(context)
    if (informations != null) {
        result.add(
            SectionHeaderComponent(
                context.getString(R.string.information)
            )
        )
        result.addAll(informations)
    }
    return result
}

fun AnimeInfo.toInformation(context: Context): List<KeyValue>? {
    val result = mutableListOf<KeyValue>()
    if (this.kind != null) {
        val value = this.kind.toPresentationAnimeKind().toString()
        result.add(
            KeyValue(
                context.getString(R.string.type),
                value,
            )
        )
    }

    var episodesValue = this.episodesAired.toString()
    if (this.episodes != this.episodesAired) {
        episodesValue += "/${this.episodes}"
    }
    result.add(
        KeyValue(
            context.getString(R.string.episodes),
            episodesValue,
        )
    )

    if (this.duration != 0) {
        result.add(
            KeyValue(
                context.getString(R.string.episode_length),
                this.duration.toString(),
            )
        )
    }

    val ratingValue = this.rating.toDataAnimeRating().toString()
    result.add(
        KeyValue(
            context.getString(R.string.rating),
            ratingValue,
        )
    )

    val japaneseValue = this.japanese.find { it != null }?.toString()
    if (japaneseValue != null) {
        result.add(
            KeyValue(
                context.getString(R.string.japanese_name),
                japaneseValue,
            )
        )
    }

    val englishValue = this.english.find { it != null }?.toString()
    if (englishValue != null) {
        result.add(
            KeyValue(
                context.getString(R.string.english_name),
                englishValue,
            )
        )
    }

    return if (result.isEmpty()) null else result
}
package com.gmkornilov.shikimori.presentation.extensions

import android.content.Context
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.domain.models.common.AnimeInfo
import com.gmkornilov.shikimori.domain.models.common.AnimeStatus
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.gmkornilov.shikimori.presentation.components.description.DescriptionComponent
import com.gmkornilov.shikimori.presentation.components.keyvalue.KeyValue
import com.gmkornilov.shikimori.presentation.components.sectionheader.SectionHeaderComponent
import com.gmkornilov.shikimori.presentation.models.common.*

val doubleBracketsRegex = Regex("(\\[\\[)|(]])")
val onceBracketsRegex = Regex("\\[.+?]")
val doubleWhitespaceRegex = Regex("\\s+")

fun AnimeInfo.toAnimePageItems(context: Context): List<BaseComponent> {
    val result = mutableListOf<BaseComponent>()

    val descriptionComponent = this.toDescription()
    if (descriptionComponent != null) {
        result.add(
            SectionHeaderComponent(
                context.getString(R.string.description)
            )
        )
        result.add(descriptionComponent)
    }

    val informationKeyValues = this.toInformation(context)
    if (informationKeyValues != null) {
        result.add(
            SectionHeaderComponent(
                context.getString(R.string.information)
            )
        )
        result.addAll(informationKeyValues)
    }
    return result
}

fun AnimeInfo.toDescription(): DescriptionComponent? {
    if (this.description == null) {
        return null
    }
    var description = this.description.replace(doubleBracketsRegex, "")
    description = onceBracketsRegex.replace(description, "")
    description = doubleWhitespaceRegex.replace(description, " ")
    return DescriptionComponent(description)
}

fun AnimeInfo.toInformation(context: Context): List<KeyValue>? {
    val result = mutableListOf<KeyValue>()

    val kindKeyValue = this.toKind(context)
    if (kindKeyValue != null) {
        result.add(kindKeyValue)
    }

    val episodesKeyValue = this.toEpisodes(context)
    if (episodesKeyValue != null) {
        result.add(episodesKeyValue)
    }

    val durationKeyValue = this.toDuration(context)
    if (durationKeyValue != null) {
        result.add(durationKeyValue)
    }

    val statusKeyValue = this.toStatus(context)
    result.add(statusKeyValue)

    val ratingKeyValue = this.toRating(context)
    if (ratingKeyValue != null) {
        result.add(ratingKeyValue)
    }

//    val ruLicenseKeyValue = this.toRuLicense(context)
//    if (ruLicenseKeyValue != null) {
//        result.add(ruLicenseKeyValue)
//    }

    val japaneseKeyValue = this.toJapanese(context)
    if (japaneseKeyValue != null) {
        result.add(japaneseKeyValue)
    }

    val englishKeyValue = this.toEnglish(context)
    if (englishKeyValue != null) {
        result.add(englishKeyValue)
    }

    return if (result.isEmpty()) null else result
}

fun AnimeInfo.toKind(context: Context): KeyValue? {
    val kind = this.kind.toPresentationAnimeKind()

    if (kind == AnimeKind.UNKNOWN) {
        return null
    }

    return KeyValue(
        context.getString(R.string.type),
        context.getString(kind.titleResourceId),
    )
}

fun AnimeInfo.toEpisodes(context: Context): KeyValue? {
    val kind = this.kind.toPresentationAnimeKind()
    if (!kind.isTv()) {
        return null
    }
    var episodesValue: String

    when (this.status) {
        AnimeStatus.ANONS -> {
            episodesValue = ""
        }
        AnimeStatus.ONGOING -> {
            episodesValue = this.episodesAired.toString()
            if (this.episodes == 0) {
                episodesValue += "/?"
            } else if (this.episodes != this.episodesAired) {
                episodesValue += "/${this.episodes}"
            }
        }
        AnimeStatus.RELEASED -> {
            episodesValue = this.episodesAired.coerceAtLeast(this.episodes).toString()
        }
    }

    return if (episodesValue.isNotEmpty()) {
        KeyValue(
            context.getString(R.string.episodes),
            episodesValue,
        )
    } else {
        null
    }
}

fun AnimeInfo.toDuration(context: Context): KeyValue? {
    val kind = this.kind.toPresentationAnimeKind()
    if (!kind.isTv() || this.duration == 0) {
        return null
    }
    return KeyValue(
        context.getString(R.string.episode_length),
        this.duration.toString(),
    )
}

fun AnimeInfo.toStatus(context: Context): KeyValue {
    return KeyValue(
        context.getString(R.string.status),
        context.getString(this.status.toPresentationAnimeStatus().stringRes)
    )
}

fun AnimeInfo.toRating(context: Context): KeyValue? {
    val rating = this.rating.toPresentationAnimeRating()
    if (rating == AnimeRating.NONE) {
        return null
    }
    return KeyValue(
        context.getString(R.string.rating),
        context.getString(rating.stringRes),
    )
}

//fun AnimeInfo.toRuLicense(context: Context): KeyValue? {
//    if (this.ruLicenseName == null || this.name == this.ruLicenseName) {
//        return null
//    }
//    return KeyValue(
//        context.getString(R.string.license_name_ru),
//        this.ruLicenseName,
//    )
//}

fun AnimeInfo.toJapanese(context: Context): KeyValue? {
    val japaneseValue = this.japanese.find { it != null }?.toString() ?: return null
    return KeyValue(
        context.getString(R.string.japanese_name),
        japaneseValue,
    )
}

fun AnimeInfo.toEnglish(context: Context): KeyValue? {
    val englishValue = this.english.find { it != null }?.toString() ?: return null
    return KeyValue(
        context.getString(R.string.english_name),
        englishValue,
    )
}

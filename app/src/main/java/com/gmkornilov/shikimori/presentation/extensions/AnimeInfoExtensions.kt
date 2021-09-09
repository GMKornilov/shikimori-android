package com.gmkornilov.shikimori.presentation.extensions

import android.content.Context
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.domain.models.common.AnimeInfo
import com.gmkornilov.shikimori.domain.models.common.AnimeStatus
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.gmkornilov.shikimori.presentation.components.description.DescriptionComponent
import com.gmkornilov.shikimori.presentation.components.genres.Genre
import com.gmkornilov.shikimori.presentation.components.genres.Genres
import com.gmkornilov.shikimori.presentation.components.keyvalue.KeyValue
import com.gmkornilov.shikimori.presentation.components.licensors.Licensor
import com.gmkornilov.shikimori.presentation.components.licensors.Licensors
import com.gmkornilov.shikimori.presentation.components.rating.Rating
import com.gmkornilov.shikimori.presentation.components.screenshots.Screenshots
import com.gmkornilov.shikimori.presentation.components.sectionheader.SectionHeaderComponent
import com.gmkornilov.shikimori.presentation.components.stat.Stat
import com.gmkornilov.shikimori.presentation.components.stat.toStat
import com.gmkornilov.shikimori.presentation.components.videos.Video
import com.gmkornilov.shikimori.presentation.components.videos.Videos
import com.gmkornilov.shikimori.presentation.models.common.*

val doubleBracketsRegex = Regex("(\\[\\[)|(]])")
val onceBracketsRegex = Regex("\\[.+?]")
val doubleWhitespaceRegex = Regex("[\\s&&[^\\n]]+")

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

    val rate = this.toRate(context)
    if (rate != null) {
        result.add(
            SectionHeaderComponent(
                context.getString(R.string.rating)
            )
        )
        result.add(rate)
    }

    val ratesStats = this.toRatesStat()
    if (ratesStats != null) {
        result.add(
            SectionHeaderComponent(
                context.getString(R.string.people_rate)
            )
        )
        result.addAll(ratesStats)
    }

    val statusesStats = this.toStatusesStat()
    if (statusesStats != null) {
        result.add(
            SectionHeaderComponent(
                context.getString(R.string.people_lists)
            )
        )
        result.addAll(statusesStats)
    }

    val screenshots = this.toScreenshots()
    if (screenshots != null) {
        result.add(
            SectionHeaderComponent(
                context.getString(R.string.screenshots)
            )
        )
        result.add(screenshots)
    }

    val videos = this.toVideos()
    if (videos != null) {
        result.add(
            SectionHeaderComponent(
                context.getString(R.string.video)
            )
        )
        result.add(videos)
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

fun AnimeInfo.toInformation(context: Context): List<BaseComponent>? {
    val result = mutableListOf<BaseComponent>()

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

    val genresValue = this.toGenres(context)
    if (genresValue != null) {
        result.add(genresValue)
    }

    val ratingKeyValue = this.toRating(context)
    if (ratingKeyValue != null) {
        result.add(ratingKeyValue)
    }

    val licensorsValue = this.toLicensors(context)
    if (licensorsValue != null) {
        result.add(licensorsValue)
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

fun AnimeInfo.toRate(context: Context): Rating? {
    if (this.score == 0.0f) {
        return null
    }
    val rate = context.getString(
        when (this.score) {
            in 9.0f..Float.MAX_VALUE -> R.string.perfect
            in 8.0f..9.0f -> R.string.great
            in 7.0f..8.0f -> R.string.good
            in 6.0f..7.0f -> R.string.normal
            in 5.0f..6.0f -> R.string.nor_good_nor_bad
            else -> R.string.bad
        }
    )
    return Rating(this.score, rate)
}

fun AnimeInfo.toRatesStat(): List<Stat>? {
    if (this.ratesScoresStats.isEmpty()) {
        return null
    }
    val maxValue = this.ratesScoresStats.maxOf { it.value }
    return this.ratesScoresStats.map { it.toStat(maxValue) }
}

fun AnimeInfo.toStatusesStat(): List<Stat>? {
    if (this.ratesStatusesStats.isEmpty()) {
        return null
    }
    val maxValue = this.ratesStatusesStats.maxOf { it.value }
    return this.ratesStatusesStats.map { it.toStat(maxValue) }
}

fun AnimeInfo.toScreenshots(): Screenshots? {
    if (this.screenshots.isNullOrEmpty()) {
        return null
    }
    val urls = this.screenshots.map { it.originalUrl }
    return Screenshots(urls)
}

fun AnimeInfo.toVideos(): Videos? {
    if (this.videos.isNullOrEmpty()) {
        return null
    }
    val videoList = this.videos.map { Video(it.imageUrl, it.url) }
    return Videos(videoList)
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

fun AnimeInfo.toGenres(context: Context): Genres? {
    if (this.genres == null) {
        return null
    }
    val genreList = this.genres.map { Genre(it.id, it.russian) }
    return Genres(
        context.getString(R.string.genres),
        genreList
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

fun AnimeInfo.toLicensors(context: Context): Licensors? {
    if (this.licensors.isNullOrEmpty()) {
        return null
    }
    val licensorList = this.licensors.map { Licensor(it) }
    return Licensors(
        context.getString(R.string.licensors),
        licensorList
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

package com.gmkornilov.shikimori.presentation.models.common

import androidx.annotation.StringRes
import com.gmkornilov.shikimori.R

enum class AnimeKind(@StringRes val titleResourceId: Int) {
    TV(R.string.tv),
    MOVIE(R.string.movie),
    OVA(R.string.ova),
    ONA(R.string.ona),
    SPECIAL(R.string.special),
    MUSIC(R.string.music),
    TV_13(R.string.tv),
    TV_24(R.string.tv),
    TV_48(R.string.tv),
    UNKNOWN(R.string.unknown);

    fun isTv(): Boolean {
        return this == TV || this == TV_13 || this == TV_24 || this == TV_48
    }
}

fun com.gmkornilov.shikimori.domain.models.common.AnimeKind?.toPresentationAnimeKind(): AnimeKind {
    return if (this == null) {
        AnimeKind.UNKNOWN
    } else {
        AnimeKind.valueOf(this.toString())
    }
}

fun AnimeKind.toDomainAnimeKind(): com.gmkornilov.shikimori.domain.models.common.AnimeKind? {
    return if (this == AnimeKind.UNKNOWN) {
        null
    } else {
        com.gmkornilov.shikimori.domain.models.common.AnimeKind.valueOf(this.toString())
    }
}
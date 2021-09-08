package com.gmkornilov.shikimori.presentation.models.common

import androidx.annotation.StringRes
import com.gmkornilov.shikimori.R as RR
import com.gmkornilov.shikimori.domain.models.common.AnimeRating as DomainAnimeRating

enum class AnimeRating(@StringRes val stringRes: Int) {
    NONE(RR.string.none),
    G(RR.string.G),
    PG(RR.string.PG),
    PG_13(RR.string.PG_13),
    R(RR.string.R),
    R_PLUS(RR.string.R_PLUS),
    RX(RR.string.RX);
}

fun DomainAnimeRating.toPresentationAnimeRating(): AnimeRating {
    return AnimeRating.valueOf(this.toString())
}
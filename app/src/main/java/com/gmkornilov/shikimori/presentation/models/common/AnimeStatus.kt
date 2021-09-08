package com.gmkornilov.shikimori.presentation.models.common

import androidx.annotation.StringRes
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.domain.models.common.AnimeStatus as DomainAnimeStatus

enum class AnimeStatus(@StringRes val stringRes: Int) {
    ANONS(R.string.announced),
    ONGOING(R.string.ongoing),
    RELEASED(R.string.released);
}

fun DomainAnimeStatus.toPresentationAnimeStatus(): AnimeStatus {
    return AnimeStatus.valueOf(this.toString())
}
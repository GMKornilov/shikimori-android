package com.gmkornilov.shikimori.presentation.filteredanimespage

import androidx.annotation.StringRes
import com.gmkornilov.shikimori.R

enum class FilteredAnimesTitleType(@StringRes val titleRes: Int) {
    NOW_ON_SCREENS(R.string.now_on_screens),
    ANNOUNCED(R.string.announcements),
    MOST_POPULAR(R.string.most_popular),
    MOST_RATED(R.string.most_rated),
    FILTERS(R.string.filtration),
}
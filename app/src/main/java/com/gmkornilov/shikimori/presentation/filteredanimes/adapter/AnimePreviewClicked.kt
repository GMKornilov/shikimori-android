package com.gmkornilov.shikimori.presentation.filteredanimes.adapter

import com.gmkornilov.shikimori.domain.models.common.AnimePreview

interface AnimePreviewClicked {
    fun animePreviewClicked(animePreview: AnimePreview)
}
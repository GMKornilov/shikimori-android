package com.gmkornilov.shikimori.presentation.animepreview

import com.gmkornilov.shikimori.domain.models.common.AnimePreview

interface AnimePreviewClicked {
    fun onClicked(animePreview: AnimePreview)
}
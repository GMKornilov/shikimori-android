package com.gmkornilov.shikimori.presentation.mainpage.adapter

import com.gmkornilov.shikimori.domain.models.common.AnimePreview

interface AnimePreviewClicked {
    fun onClicked(animePreview: AnimePreview)
}
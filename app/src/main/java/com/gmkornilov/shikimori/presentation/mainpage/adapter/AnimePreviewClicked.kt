package com.gmkornilov.shikimori.presentation.mainpage.adapter

import com.gmkornilov.shikimori.domain.models.mainpage.AnimePreview

interface AnimePreviewClicked {
    fun onClicked(animePreview: AnimePreview)
}
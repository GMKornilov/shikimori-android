package com.gmkornilov.shikimori.presentation.animepreview

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

class AnimePreviewAdapter(
    private val animePreviewClicked: AnimePreviewClicked,
) : ListAdapter<AnimePreview, AnimeRowViewHolder>(AnimePreview.config) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeRowViewHolder {
        return AnimeRowViewHolder.from(parent, animePreviewClicked)
    }

    override fun onBindViewHolder(holder: AnimeRowViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
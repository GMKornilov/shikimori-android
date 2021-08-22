package com.gmkornilov.shikimori.presentation.animepreview

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.gmkornilov.shikimori.domain.models.common.AnimePreview

class AnimePreviewAdapter(
    private val animePreviewClicked: AnimePreviewClicked,
) : ListAdapter<AnimePreview, AnimeRowViewHolder>(config) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeRowViewHolder {
        return AnimeRowViewHolder.from(parent, animePreviewClicked)
    }

    override fun onBindViewHolder(holder: AnimeRowViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {

        private val DIFF = object : DiffUtil.ItemCallback<AnimePreview>() {
            override fun areItemsTheSame(oldItem: AnimePreview, newItem: AnimePreview): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AnimePreview, newItem: AnimePreview): Boolean {
                return oldItem.name == newItem.name
                        && oldItem.kind == newItem.kind
                        && oldItem.airedOn == newItem.airedOn
            }
        }
        val config by lazy(LazyThreadSafetyMode.NONE) {
            AsyncDifferConfig.Builder(DIFF).build()
        }
    }
}
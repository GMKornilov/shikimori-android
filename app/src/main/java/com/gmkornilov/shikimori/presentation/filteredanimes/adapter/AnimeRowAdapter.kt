package com.gmkornilov.shikimori.presentation.filteredanimes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.domain.models.common.AnimePreview

class AnimeRowAdapter(
    private val animePreviewClicked: AnimePreviewClicked,
) : RecyclerView.Adapter<AnimeRowViewHolder>() {
    private var data: List<AnimePreview> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeRowViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.anime_row_preview_layout, parent, false)
        return AnimeRowViewHolder(view, animePreviewClicked)
    }

    override fun onBindViewHolder(holder: AnimeRowViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: List<AnimePreview>) {
        data = newData
        notifyDataSetChanged()
    }
}
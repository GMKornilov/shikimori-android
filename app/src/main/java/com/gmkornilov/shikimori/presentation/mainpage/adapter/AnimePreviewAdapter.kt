package com.gmkornilov.shikimori.presentation.mainpage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmkornilov.shikimori.databinding.AnimePreviewLayoutBinding
import com.gmkornilov.shikimori.domain.models.mainpage.AnimePreview

class AnimePreviewAdapter(
    private val animePreviewClicked: AnimePreviewClicked,
) : RecyclerView.Adapter<AnimePreviewViewHolder>() {
    private var data: List<AnimePreview> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimePreviewViewHolder {
        val binding =
            AnimePreviewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimePreviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimePreviewViewHolder, position: Int) {
        holder.bind(data[position], animePreviewClicked)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: List<AnimePreview>) {
        data = newData
        notifyDataSetChanged()
    }
}
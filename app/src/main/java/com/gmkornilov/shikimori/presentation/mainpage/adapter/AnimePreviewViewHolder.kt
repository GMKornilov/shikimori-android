package com.gmkornilov.shikimori.presentation.mainpage.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.gmkornilov.shikimori.databinding.AnimePreviewLayoutBinding
import com.gmkornilov.shikimori.domain.models.mainpage.AnimePreview

class AnimePreviewViewHolder(private val binding: AnimePreviewLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val shimmer = Shimmer.AlphaHighlightBuilder()
        .setDuration(DURATION)
        .setBaseAlpha(BASE_ALPHA)
        .setHighlightAlpha(HIGHLIGHT_ALPHA)
        .setDirection(DIRECTION)
        .setAutoStart(AUTO_START)
        .build()

    private val shimmerDrawable = ShimmerDrawable().apply {
        setShimmer(shimmer)
    }

    fun bind(animePreview: AnimePreview, animePreviewClicked: AnimePreviewClicked) {
        binding.animeName.text = animePreview.title

        Glide.with(binding.root)
            .load(animePreview.thumbnailUrl)
            .placeholder(shimmerDrawable)
            .fitCenter()
            .into(binding.animePreviewImage)

        binding.root.setOnClickListener {
            animePreviewClicked.onClicked(animePreview)
        }
    }

    companion object {
        private const val DURATION = 1000L
        private const val BASE_ALPHA = 0.7f
        private const val HIGHLIGHT_ALPHA = 0.6f
        private const val DIRECTION = Shimmer.Direction.LEFT_TO_RIGHT
        private const val AUTO_START = true
    }
}
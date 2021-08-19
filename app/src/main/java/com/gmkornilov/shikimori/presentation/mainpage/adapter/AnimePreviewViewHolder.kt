package com.gmkornilov.shikimori.presentation.mainpage.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.domain.models.common.AnimePreview

class AnimePreviewViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view) {

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

    private val animeName: TextView = view.findViewById(R.id.animeName)
    private val animePreviewImage: ImageView = view.findViewById(R.id.animePreviewImage)

    fun bind(animePreview: AnimePreview, animePreviewClicked: AnimePreviewClicked) {
        animeName.text = animePreview.name

        Glide.with(animePreviewImage)
            .load(animePreview.imageInfo.urlPreview)
            .placeholder(shimmerDrawable)
            .fitCenter()
            .into(animePreviewImage)

        view.setOnClickListener {
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
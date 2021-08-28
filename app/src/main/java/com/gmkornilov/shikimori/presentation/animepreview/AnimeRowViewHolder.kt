package com.gmkornilov.shikimori.presentation.animepreview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import java.text.SimpleDateFormat
import java.util.*

class AnimeRowViewHolder(
    private val view: View,
    private val animePreviewClicked: AnimePreviewClicked
) : RecyclerView.ViewHolder(view) {
    private val shimmer = Shimmer.AlphaHighlightBuilder()
        .setDuration(DURATION)
        .setBaseAlpha(BASE_ALPHA)
        .setHighlightAlpha(HIGHLIGHT_ALPHA)
        .setDirection(DIRECTION)
        .setAutoStart(AUTO_START)
        .build()

    private val dateFormat = SimpleDateFormat("yyyy", Locale.ENGLISH)

    private val shimmerDrawable = ShimmerDrawable().apply {
        setShimmer(shimmer)
    }

    private val thumbnailImage: ImageView = view.findViewById(R.id.thumbnailImage)

    private val titleText: TextView = view.findViewById(R.id.titleText)

    private val kindText: TextView = view.findViewById(R.id.kindText)

    private val releaseYearText: TextView = view.findViewById(R.id.releaseYearText)

    fun bind(animePreview: AnimePreview) {
        Glide.with(thumbnailImage)
            .load(animePreview.imageInfo.urlPreview)
            .placeholder(shimmerDrawable)
            .into(thumbnailImage)

        titleText.text = animePreview.name

        // TODO: add enum to title resource id mapping
        kindText.text = animePreview.kind.toString()

        if (animePreview.airedOn != null) {
            releaseYearText.text = dateFormat.format(animePreview.airedOn)
        } else {
            releaseYearText.visibility = View.GONE
        }

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

        fun from(parent: ViewGroup, animePreviewClicked: AnimePreviewClicked): AnimeRowViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.anime_row_preview_layout, parent, false)
            return AnimeRowViewHolder(view, animePreviewClicked)
        }
    }
}
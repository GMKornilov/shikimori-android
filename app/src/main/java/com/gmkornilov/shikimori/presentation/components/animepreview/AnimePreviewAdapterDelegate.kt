package com.gmkornilov.shikimori.presentation.components.animepreview

import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.gmkornilov.shikimori.databinding.AnimeRowPreviewLayoutBinding
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun animePreviewAdapterDelegate(animePreviewClicked: AnimePreviewClicked) =
    adapterDelegateViewBinding<AnimePreview, BaseComponent, AnimeRowPreviewLayoutBinding>(
        { layoutInflater, parent ->
            AnimeRowPreviewLayoutBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        }
    ) {
        binding.root.setOnClickListener {
            animePreviewClicked.onClicked(item)
        }

        bind {
            with(binding) {
                Glide.with(thumbnailImage)
                    .load(item.thumbnailImageUrl)
                    .placeholder(shimmerDrawable)
                    .into(thumbnailImage)

                titleText.text = item.titleText

                kindText.text = root.context.getString(item.kind.titleResourceId)

                releaseYearText.text = item.airedOnYearString
            }
        }
    }

private const val DURATION = 1000L
private const val BASE_ALPHA = 0.7f
private const val HIGHLIGHT_ALPHA = 0.6f
private const val DIRECTION = Shimmer.Direction.LEFT_TO_RIGHT
private const val AUTO_START = true

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

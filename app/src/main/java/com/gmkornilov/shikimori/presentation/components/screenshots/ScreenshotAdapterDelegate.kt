package com.gmkornilov.shikimori.presentation.components.screenshots

import android.view.View
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.gmkornilov.shikimori.databinding.ItemScreenshotsBinding
import com.gmkornilov.shikimori.databinding.ScreenshotItemLayoutBinding
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun screenshotAdapterDelegate() =
    adapterDelegateViewBinding<Screenshots, BaseComponent, ItemScreenshotsBinding>(
        { layoutInflater, parent -> ItemScreenshotsBinding.inflate(layoutInflater, parent, false) }
    ) {


        bind {
            val firstUrl = item.urls[0]
            val secondUrl = item.urls.getOrNull(1)

            Glide.with(binding.screenshotImageFirst)
                .load(firstUrl)
                .placeholder(shimmerDrawable)
                .into(binding.screenshotImageFirst)

            if (secondUrl == null) {
                binding.screenshotImageSecond.visibility = View.GONE
            } else {
                Glide.with(binding.screenshotImageSecond)
                    .load(secondUrl)
                    .placeholder(shimmerDrawable)
                    .into(binding.screenshotImageSecond)
            }

        }
    }


fun screenshotItemAdapterDelegate() = adapterDelegateViewBinding<Screenshot, BaseComponent, ScreenshotItemLayoutBinding>(
    {layoutInflater, parent -> ScreenshotItemLayoutBinding.inflate(layoutInflater, parent, false) }
) {
    bind {
        Glide.with(binding.screenshotImage)
            .load(item.url)
            .placeholder(shimmerDrawable)
            .into(binding.screenshotImage)
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
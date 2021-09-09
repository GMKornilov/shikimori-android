package com.gmkornilov.shikimori.presentation.components.videos

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.databinding.ItemVideosBinding
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun videoAdapterDelegate(onClick: (String) -> Unit) =
    adapterDelegateViewBinding<Videos, BaseComponent, ItemVideosBinding>(
        { layoutInflater, parent -> ItemVideosBinding.inflate(layoutInflater, parent, false) }
    ) {
        val firstContentListener = object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                Log.e(TAG, "$e")
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                binding.firstVideoPlay.visibility = View.VISIBLE
                return false
            }
        }

        val secondContentListener = object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                Log.e(TAG, "$e")
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                binding.secondVideoPlay.visibility = View.VISIBLE
                return false
            }
        }

        bind {
            with(binding) {
                val firstVideo = item.videos[0]
                val secondVideo = item.videos.getOrNull(1)

                firstVideoPlay.visibility = View.GONE
                Glide.with(videoThumbnailFirst)
                    .load(firstVideo.thumbnailUrl)
                    .placeholder(shimmerDrawableFirst)
                    .error(R.drawable.ic_sad)
                    .fitCenter()
                    .listener(firstContentListener)
                    .into(videoThumbnailFirst)
                firstVideoPlay.setOnClickListener {
                    onClick(firstVideo.videoUrl)
                }

                if (secondVideo == null) {
                    videoContentSecond.visibility = View.GONE
                } else {
                    secondVideoPlay.visibility = View.GONE
                    Glide.with(videoThumbnailSecond)
                        .load(secondVideo.thumbnailUrl)
                        .placeholder(shimmerDrawableSecond)
                        .error(R.drawable.ic_sad)
                        .fitCenter()
                        .listener(secondContentListener)
                        .into(videoThumbnailSecond)
                    secondVideoPlay.setOnClickListener {
                        onClick(secondVideo.videoUrl)
                    }
                }
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

private val shimmerDrawableFirst = ShimmerDrawable().apply {
    setShimmer(shimmer)
}

private val shimmerDrawableSecond = ShimmerDrawable().apply {
    setShimmer(shimmer)
}

private const val TAG = "VideoAdapterDelegate"
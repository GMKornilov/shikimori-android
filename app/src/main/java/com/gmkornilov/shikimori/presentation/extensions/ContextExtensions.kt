package com.gmkornilov.shikimori.presentation.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources

fun Context.getAppCompatDrawable(@DrawableRes res: Int): Drawable? {
    return AppCompatResources.getDrawable(this, res)
}
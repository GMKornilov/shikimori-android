package com.gmkornilov.shikimori.presentation.extensions

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.mapVisibility(isVisible: Boolean): Int {
    return if (isVisible) View.VISIBLE else View.GONE
}

fun Fragment.mapVisibility(isVisible: Boolean): Int {
    return if (isVisible) View.VISIBLE else View.GONE
}
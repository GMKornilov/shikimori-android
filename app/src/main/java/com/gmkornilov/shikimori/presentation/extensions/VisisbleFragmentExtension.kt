package com.gmkornilov.shikimori.presentation.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.visibleFragment(): Fragment? {
    for (fragment in this.fragments) {
        if (fragment.isVisible) {
            return fragment
        }
    }
    return null
}
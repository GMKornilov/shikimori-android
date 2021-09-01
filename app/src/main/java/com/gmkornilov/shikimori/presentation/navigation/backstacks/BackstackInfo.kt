package com.gmkornilov.shikimori.presentation.navigation.backstacks

import android.os.Parcelable
import com.github.terrakok.cicerone.Screen

interface BackstackInfo: Parcelable {
    fun getBackstackName(): String

    fun getRootScreen(): Screen
}
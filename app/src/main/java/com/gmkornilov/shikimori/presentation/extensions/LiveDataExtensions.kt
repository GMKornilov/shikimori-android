package com.gmkornilov.shikimori.presentation.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

fun <T, R> LiveData<T>.map(mapper: (T) -> R): LiveData<R> {
    return Transformations.map(this, mapper)
}

fun <T, R> LiveData<T>.switchMap(mapper: (T) -> LiveData<R>): LiveData<R> {
    return Transformations.switchMap(this, mapper)
}
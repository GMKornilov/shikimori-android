package com.gmkornilov.shikimori.domain.interactors

import io.reactivex.rxjava3.core.Completable

interface CompletableUseCase<Params> {
    fun buildCompletable(params: Params): Completable
}
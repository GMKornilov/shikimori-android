package com.gmkornilov.shikimori.domain.interactors

import io.reactivex.rxjava3.core.Completable

/**
 * Use case implementation, which doesn't emit any data
 */
interface CompletableUseCase<Params> {
    fun buildCompletable(params: Params): Completable
}
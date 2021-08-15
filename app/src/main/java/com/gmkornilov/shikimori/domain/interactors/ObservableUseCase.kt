package com.gmkornilov.shikimori.domain.interactors

import io.reactivex.rxjava3.core.Observable

/**
 * Use case implementation, which emits unknown amount of data
 */
interface ObservableUseCase<Params, Type> {
    fun buildObservable(params: Params): Observable<Type>
}
package com.gmkornilov.shikimori.domain.interactors

import io.reactivex.rxjava3.core.Observable

interface ObservableUseCase<Params, Type> {
    fun buildObservable(params: Params): Observable<Type>
}
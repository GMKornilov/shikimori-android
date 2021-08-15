package com.gmkornilov.shikimori.domain.interactors

import io.reactivex.rxjava3.core.Single

interface SingleUseCase<Params, Type> {
    fun buildSingle(params: Params): Single<Type>
}
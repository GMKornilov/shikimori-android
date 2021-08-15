package com.gmkornilov.shikimori.domain.interactors

import io.reactivex.rxjava3.core.Single

/**
 * Implementation of use case, which emits exactly one value
 */
interface SingleUseCase<Params, Type> {
    fun buildSingle(params: Params): Single<Type>
}
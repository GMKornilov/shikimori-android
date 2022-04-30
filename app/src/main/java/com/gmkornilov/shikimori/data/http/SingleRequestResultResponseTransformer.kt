package com.gmkornilov.shikimori.data.http

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleSource
import io.reactivex.rxjava3.core.SingleTransformer
import retrofit2.Response

class SingleRequestResultResponseTransformer<T>: SingleTransformer<Response<T>, RequestResult<T>> {
    override fun apply(upstream: Single<Response<T>>): SingleSource<RequestResult<T>> {
        return upstream
            .map { it.toRequestResult() }
            .onErrorReturn { it.toRequestResult() }
    }
}
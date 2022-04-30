package com.gmkornilov.shikimori.data.http

import android.util.Log
import io.reactivex.rxjava3.core.Single
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException

private const val EMPTY_BODY_MSG =
    "Empty body. For Retrofit methods with Unit return type use another mapper"

fun <T> Single<Response<T>>.responseToRequestResult() =
    compose(SingleRequestResultResponseTransformer())

fun <T, R> Single<RequestResult<T>>.mapSuccessful(mapFunction: (T) -> R): Single<RequestResult<R>> =
    map {
        when (it) {
            is RequestResult.Success -> RequestResult.Success(mapFunction(it.data))
            is RequestResult.Failure -> it.toAnotherType()
        }
    }

private fun <T, R> RequestResult.Failure<T>.toAnotherType(): RequestResult.Failure<R> {
    return when (this) {
        is RequestResult.Failure.IoError.EmptyResponse -> RequestResult.Failure.IoError.EmptyResponse(
            this.throwable
        )
        is RequestResult.Failure.IoError.OtherIoError -> RequestResult.Failure.IoError.OtherIoError(
            this.throwable
        )
        is RequestResult.Failure.IoError.TimeoutError -> RequestResult.Failure.IoError.TimeoutError(
            this.throwable
        )
        is RequestResult.Failure.HttpError -> RequestResult.Failure.HttpError(this.code, this.body)
    }
}

fun <T> Response<T>.toRequestResult(): RequestResult<T> {
    return if (this.isSuccessful) {
        val body = this.body()
        if (body == null) {
            Log.d("empty body", EMPTY_BODY_MSG)
            return RequestResult.Failure.IoError.EmptyResponse(Throwable(EMPTY_BODY_MSG))
        } else {
            return RequestResult.Success(body)
        }
    } else {
        RequestResult.Failure.HttpError(this.code(), this.errorBody()?.string())
    }
}

fun <T> Throwable.toRequestResult(): RequestResult<T> = when (this) {
    is HttpException -> RequestResult.Failure.HttpError(
        this.code(),
        this.response()?.errorBody()?.string()
    )
    is TimeoutException -> RequestResult.Failure.IoError.TimeoutError(this)
    is SocketTimeoutException -> RequestResult.Failure.IoError.TimeoutError(this)
    is IOException -> RequestResult.Failure.IoError.OtherIoError(this)
    else -> throw this
}
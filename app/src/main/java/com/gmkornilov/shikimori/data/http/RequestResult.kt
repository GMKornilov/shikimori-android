package com.gmkornilov.shikimori.data.http

sealed class RequestResult<T> {
    data class Success<T> (
        val data: T,
    ): RequestResult<T>()

    sealed class Failure<T>: RequestResult<T>() {
        sealed class IoError<T>: Failure<T>() {
            abstract val throwable: Throwable

            data class EmptyResponse<T>(override val throwable: Throwable): IoError<T>()

            data class TimeoutError<T>(override val throwable: Throwable): IoError<T>()

            data class OtherIoError<T>(override val throwable: Throwable): IoError<T>()
        }

        data class HttpError<T>(val code: Int, val body: String?): Failure<T>()
    }
}

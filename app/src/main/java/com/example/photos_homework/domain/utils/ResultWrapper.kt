package com.example.photos_homework.domain.utils

import timber.log.Timber

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()

    data class Error(val throwable: Throwable? = null) : ResultWrapper<Nothing>()

    data object None : ResultWrapper<Nothing>()

    data object Loading : ResultWrapper<Nothing>()

    companion object {
        fun <T> success(value: T) = Success(value)
        fun loading() = Loading
        fun none() = None
        fun error(throwable: Throwable?): Error {
            Timber.e(throwable)
            return Error(throwable)
        }
    }

    @Throws(Exception::class)
    fun takeValueOrThrow(): T {
        return when (this) {
            is Success -> value
            is Error -> throw throwable ?: Throwable()
            else -> throw Throwable("Unknown the result type $this")
        }
    }
}
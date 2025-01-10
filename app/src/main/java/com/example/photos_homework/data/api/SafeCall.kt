package com.example.photos_homework.data.api

import com.example.photos_homework.domain.utils.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import kotlin.coroutines.cancellation.CancellationException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    call: suspend CoroutineScope.() -> T,
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(call())
        } catch (e: CancellationException) {
            Timber.e(e)
            ResultWrapper.Error(e)
        } catch (e: Throwable) {
            Timber.e(e)
            ResultWrapper.Error(e)
        }
    }
}
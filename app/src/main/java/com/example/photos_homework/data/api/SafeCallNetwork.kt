package com.example.photos_homework.data.api

import com.example.photos_homework.domain.utils.NetworkError
import com.example.photos_homework.domain.utils.Result
import kotlinx.coroutines.ensureActive
import retrofit2.Response
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCallApi(
    execute: () -> Response<T>,
): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(NetworkError())
    }
    return responseToResult(response)
}

inline fun <reified T> responseToResult(response: Response<T>)
        : Result<T, NetworkError> {
    return when (response.code()) {
        in 200..299 ->
            try {
                if (response.isSuccessful && response.body() != null) {
                    Result.Success(response.body()!!)
                } else {
                    Result.Error(NetworkError())
                }
            } catch (e: Exception) {
                Result.Error(NetworkError())
            }

        else -> Result.Error(NetworkError())
    }
}
package com.example.photos_homework.data.api

import com.example.photos_homework.data.network.ResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface PhotoApi {
    @GET("photos")
    suspend fun fetchPhotos(): Response<ResponseDto>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}
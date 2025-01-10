package com.example.photos_homework.data.api

import com.example.photos_homework.data.network.PhotoDto
import retrofit2.http.GET

interface PhotoApi {
    @GET("photos")
    suspend fun fetchPhotos(): List<PhotoDto>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}
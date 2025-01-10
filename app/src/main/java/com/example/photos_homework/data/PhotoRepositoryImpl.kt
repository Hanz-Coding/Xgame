package com.example.photos_homework.data

import com.example.photos_homework.data.api.PhotoApi
import com.example.photos_homework.data.api.safeApiCall
import com.example.photos_homework.domain.Photo
import com.example.photos_homework.domain.repository.PhotoRepository
import com.example.photos_homework.domain.utils.ResultWrapper

class PhotoRepositoryImpl(private val api: PhotoApi) : PhotoRepository {
    override suspend fun fetchPhoto(): ResultWrapper<List<Photo>> {
        return safeApiCall {
            api.fetchPhotos().map { it.toDomain() }
        }
    }
}
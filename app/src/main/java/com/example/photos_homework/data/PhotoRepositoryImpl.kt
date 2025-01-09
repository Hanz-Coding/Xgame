package com.example.photos_homework.data

import com.example.photos_homework.data.api.PhotoApi
import com.example.photos_homework.data.network.ResponseDto
import com.example.photos_homework.data.api.safeCallApi
import com.example.photos_homework.domain.Photo
import com.example.photos_homework.domain.PhotoRepository
import com.example.photos_homework.domain.utils.NetworkError
import com.example.photos_homework.domain.utils.Result
import com.example.photos_homework.domain.utils.map

class PhotoRepositoryImpl(private val api: PhotoApi) : PhotoRepository {
    override suspend fun fetchPhoto(): Result<List<Photo>, NetworkError> {
        return safeCallApi<ResponseDto> {
            api.fetchPhotos()
        }.map { responseDto ->
            responseDto.data.map { photoDto ->
                photoDto.toDomain()
            }
        }
    }
}
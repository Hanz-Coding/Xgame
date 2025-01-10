package com.example.photos_homework.domain.repository

import com.example.photos_homework.domain.Photo
import com.example.photos_homework.domain.utils.ResultWrapper

interface PhotoRepository {
    suspend fun fetchPhoto(): ResultWrapper<List<Photo>>
}
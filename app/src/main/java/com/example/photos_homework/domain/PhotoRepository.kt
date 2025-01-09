package com.example.photos_homework.domain

import com.example.photos_homework.domain.utils.NetworkError
import com.example.photos_homework.domain.utils.Result

interface PhotoRepository {
    suspend fun fetchPhoto(): Result<List<Photo>, NetworkError>
}
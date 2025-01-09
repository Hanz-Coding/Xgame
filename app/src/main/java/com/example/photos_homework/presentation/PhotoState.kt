package com.example.photos_homework.presentation

import com.example.photos_homework.domain.Photo

data class PhotoState(
    val isLoading: Boolean = false,
    val photos: List<Photo> = emptyList(),
)

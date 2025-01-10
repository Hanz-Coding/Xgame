package com.example.photos_homework.data

import com.example.photos_homework.data.network.PhotoDto
import com.example.photos_homework.domain.Photo

fun PhotoDto.toDomain(): Photo {
    return Photo(
        albumId = albumId ?: 0,
        id = id ?: 0,
        title = title ?: "",
        url = url ?: "",
        thumbnailUrl = thumbnailUrl ?: ""
    )
}
package com.example.photos_homework.data

import com.example.photos_homework.data.network.PhotoDto
import com.example.photos_homework.domain.Photo

fun PhotoDto.toDomain(): Photo {
    return Photo(
        albumId = albumId,
        id = id,
        title = title,
        url = url,
        thumbnailUrl = thumbnailUrl
    )
}
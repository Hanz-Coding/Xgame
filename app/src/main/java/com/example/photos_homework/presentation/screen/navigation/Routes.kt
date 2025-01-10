package com.example.photos_homework.presentation.screen.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object NavGraph : Routes

    @Serializable
    data object PhotoList : Routes

    @Serializable
    data class PhotoDetail(val photoId: Int) : Routes
}
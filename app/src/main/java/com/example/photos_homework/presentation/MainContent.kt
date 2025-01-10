package com.example.photos_homework.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.example.photos_homework.presentation.screen.detail.PhotoDetailScreen
import com.example.photos_homework.presentation.screen.list.PhotoListScreen

@Composable
fun MainContent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.NavGraph
    ) {
        navigation<Routes.NavGraph>(
            startDestination = Routes.PhotoList
        ) {
            composable<Routes.PhotoList> {
                PhotoListScreen(navController)
            }

            composable<Routes.PhotoDetail> { entry ->
                val args = entry.toRoute<Routes.PhotoDetail>()
                PhotoDetailScreen(args.photoId)
            }
        }
    }
}
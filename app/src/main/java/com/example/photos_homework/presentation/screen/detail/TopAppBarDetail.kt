@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.photos_homework.presentation.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.photos_homework.R
import com.example.photos_homework.domain.Photo
import com.example.photos_homework.presentation.viewmodel.PhotoViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TopAppBarDetail(navController: NavHostController, photo: Photo) {
    val viewModel = koinViewModel<PhotoViewModel>()
    val context = LocalContext.current
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = context.getString(R.string.title_photo_detail),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                )
            }
        },
        actions = {
            IconButton(onClick = {
                viewModel.updateFavoriteStatusDetail(
                    photoId = photo.id
                )
            }) {
                Icon(
                    imageVector = if (photo.isFavorite) Icons.Outlined.Star else Icons.Outlined.StarOutline,
                    contentDescription = "Star"
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}
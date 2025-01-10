package com.example.photos_homework.presentation.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.photos_homework.domain.Photo
import com.example.photos_homework.domain.utils.ResultWrapper
import com.example.photos_homework.presentation.viewmodel.PhotoViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PhotoDetailScreen(navController: NavHostController, photoId: Int) {
    val viewModel = koinViewModel<PhotoViewModel>()
    when (val result = viewModel.detailResult.collectAsState().value) {
        is ResultWrapper.Success -> {
            val photo = result.value
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding(),
                topBar = { TopAppBarDetail(navController,photo) }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    PhotoDetailContent(photo)
                }
            }
        }

        else -> {}
    }
    LaunchedEffect(key1 = Unit, block = {
        viewModel.loadDetailPhoto(photoId)
    })
}

@Composable
fun PhotoDetailContent(photo: Photo) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(photo.thumbnailUrl)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(36.dp)
                .size(300.dp)
                .padding(8.dp),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = photo.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 36.dp),
            textAlign = TextAlign.Center
        )
    }
}

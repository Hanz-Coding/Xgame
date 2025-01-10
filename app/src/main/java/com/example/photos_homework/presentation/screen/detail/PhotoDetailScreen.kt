package com.example.photos_homework.presentation.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.photos_homework.presentation.screen.list.PhotoListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PhotoDetailScreen(photoId: Int) {
    val viewModel = koinViewModel<PhotoListViewModel>()
    val photo = viewModel.cacheRepository.cachePhotoList?.find { it.id == photoId }
    if (photo == null) return
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

@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.photos_homework.presentation.screen.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.photos_homework.R
import com.example.photos_homework.presentation.viewmodel.PhotoViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TopAppBarMain() {
    val viewModel = koinViewModel<PhotoViewModel>()
    val favoriteState by viewModel.filterFavoriteState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
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
                    text = if (favoriteState) context.getString(R.string.title_photo_list_favourite)
                    else context.getString(R.string.title_photo_list),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                )
            }
        },
        actions = {
            IconButton(onClick = {
                viewModel.filterFavouritePhoto(!favoriteState)
            }) {
                Icon(
                    imageVector = if (favoriteState) Icons.Outlined.Star
                    else Icons.Outlined.StarOutline,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(4.dp),
                    contentDescription = "Star"
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
    )
}
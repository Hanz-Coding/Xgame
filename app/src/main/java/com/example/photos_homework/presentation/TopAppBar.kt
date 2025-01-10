@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.photos_homework.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TopAppBarMain(eventHandler: EventHandler) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        title = {
            Text("Title", style = MaterialTheme.typography.titleLarge)
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Star, contentDescription = "Star")
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}
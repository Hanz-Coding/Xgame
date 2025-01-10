package com.example.photos_homework.presentation.screen.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.photos_homework.domain.Photo
import com.example.photos_homework.domain.utils.ResultWrapper
import com.example.photos_homework.presentation.Routes
import org.koin.androidx.compose.koinViewModel

@Composable
fun PhotoListScreen(navController: NavHostController) {
    val viewModel = koinViewModel<PhotoListViewModel>()
    var searchQuery by remember { mutableStateOf("") }

    when (val result = viewModel.photoResult.collectAsStateWithLifecycle().value) {
        is ResultWrapper.Success -> {
            println("hanz PhotoListScreen")
            Column(modifier = Modifier.fillMaxSize()) {
                SearchTextField(searchQuery) { newQuery ->
                    searchQuery = newQuery
                }
                val filteredItems =
                    result.value.filter { it.title.contains(searchQuery, ignoreCase = true) }

                ContentUI(
                    photoList = result.value,
                    onClickItem = { navController.navigate(Routes.PhotoDetail(it)) },
                    onClickStar = {
                        viewModel.updateFavoriteStatus(it)
                    }
                )
            }
        }

        else -> {}
    }
    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchPhoto()
    })
}

@Composable
fun SearchTextField(searchQuery: String, onQueryChanged: (String) -> Unit) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = { onQueryChanged(it) },
        label = { Text("Search") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(onClick = { onQueryChanged("") }) {
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear")
                }
            }
        },
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
    )
}


@Composable
fun ContentUI(
    photoList: List<Photo>,
    onClickItem: (Int) -> Unit,
    onClickStar: (Int) -> Unit,
) {
    println("hanz ContentUI")
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(photoList) {
            PhotoItem(it, onClickItem, onClickStar)
        }
    }
}

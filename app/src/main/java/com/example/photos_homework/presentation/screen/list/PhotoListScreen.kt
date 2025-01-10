package com.example.photos_homework.presentation.screen.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.photos_homework.domain.Photo
import com.example.photos_homework.domain.utils.ResultWrapper
import com.example.photos_homework.presentation.screen.navigation.Routes
import com.example.photos_homework.presentation.viewmodel.PhotoViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PhotoListScreen(navController: NavHostController) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding(), topBar = { TopAppBarMain() }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            PhotoListContent(navController)
        }
    }
}

@Composable
fun PhotoListContent(navController: NavHostController) {
    val viewModel = koinViewModel<PhotoViewModel>()

    var searchQuery by remember { mutableStateOf("") }
    val filterState by viewModel.filterFavoriteState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.loadCurrentListPhoto()
    })

    when (val result = viewModel.state.collectAsStateWithLifecycle().value) {
        is ResultWrapper.Success -> {
            Column(modifier = Modifier.fillMaxSize()) {
                SearchTextField(searchQuery) { newQuery ->
                    searchQuery = newQuery
                }
                val searchResult = viewModel.getFavouriteList(result.value, searchQuery)

                ContentUI(photoList = searchResult,
                    isFilterFavourites = filterState,
                    onClickItem = { navController.navigate(Routes.PhotoDetail(it)) },
                    onClickStar = {
                        viewModel.updateFavoriteStatus(it)
                    })
            }
        }

        is ResultWrapper.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        else -> {}
    }
}

@Composable
fun ContentUI(
    photoList: List<Photo>,
    isFilterFavourites: Boolean = false,
    onClickItem: (Int) -> Unit,
    onClickStar: (Int) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        val list = if (isFilterFavourites) photoList.filter { it.isFavorite } else photoList
        items(list) {
            PhotoItem(it, onClickItem, onClickStar)
        }
    }
}

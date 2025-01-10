package com.example.photos_homework.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photos_homework.domain.Photo
import com.example.photos_homework.domain.repository.CacheRepository
import com.example.photos_homework.domain.repository.PhotoRepository
import com.example.photos_homework.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PhotoViewModel(
    private val repository: PhotoRepository,
    private val cacheRepository: CacheRepository,
) : ViewModel() {
    private val photoResult = MutableStateFlow<ResultWrapper<List<Photo>>>(ResultWrapper.None)
    val detailResult = MutableStateFlow<ResultWrapper<Photo>>(ResultWrapper.None)
    private var _filterFavoriteState = MutableStateFlow(false)
    val filterFavoriteState = _filterFavoriteState.asStateFlow()

    val state = photoResult.onStart {
        fetchPhoto()
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), ResultWrapper.None
    )

    private fun fetchPhoto() {
        viewModelScope.launch {
            photoResult.emit(ResultWrapper.Loading)
            cacheRepository.cachePhotoList?.let {
                photoResult.emit(ResultWrapper.success(it))
            }
            photoResult.emit(repository.fetchPhoto().apply {
                if (this is ResultWrapper.Success) {
                    cacheRepository.cachePhotoList = value.toMutableList()
                }
            })
        }
    }

    fun updateFavoriteStatus(photoId: Int) {
        viewModelScope.launch {
            cacheRepository.cachePhotoList?.let { list ->
                val resultList = getListWithUpdateFavouriteId(list, photoId)
                cacheRepository.cachePhotoList = resultList.toMutableList()
                photoResult.emit(ResultWrapper.success(resultList))
            }
        }
    }

    fun updateFavoriteStatusDetail(photoId: Int) {
        viewModelScope.launch {
            cacheRepository.cachePhotoList?.let { list ->
                val resultList = getListWithUpdateFavouriteId(list, photoId)
                cacheRepository.cachePhotoList = resultList.toMutableList()
                resultList.find { it.id == photoId }?.let {
                    detailResult.emit(ResultWrapper.success(it))
                }
            }
        }
    }


    private fun getListWithUpdateFavouriteId(list: MutableList<Photo>, photoId: Int): List<Photo> {
        return list.map {
            if (it.id == photoId) {
                it.copy(isFavorite = !it.isFavorite)
            } else {
                it
            }
        }
    }

    fun loadDetailPhoto(photoId: Int) {
        viewModelScope.launch {
            cacheRepository.cachePhotoList?.find { it.id == photoId }?.let {
                detailResult.emit(ResultWrapper.success(it))
            }
        }
    }

    fun filterFavouritePhoto(isFavourite: Boolean) {
        viewModelScope.launch {
            _filterFavoriteState.update { isFavourite }
            cacheRepository.cachePhotoList?.let { list ->
                val resultList = if (isFavourite) list.filter { it.isFavorite }
                else list.toMutableList()
                photoResult.emit(ResultWrapper.success(resultList))
            }
        }
    }

    fun loadCurrentListPhoto() {
        viewModelScope.launch {
            cacheRepository.cachePhotoList?.let { list ->
                photoResult.emit(ResultWrapper.success(list.toMutableList()))
            }
        }
    }

    fun getFavouriteList(list: List<Photo>, searchQuery: String): List<Photo> {
        return list.filter { it.title.contains(searchQuery, ignoreCase = true) }
    }
}
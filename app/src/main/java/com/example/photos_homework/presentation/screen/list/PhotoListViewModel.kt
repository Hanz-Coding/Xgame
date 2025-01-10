package com.example.photos_homework.presentation.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photos_homework.domain.Photo
import com.example.photos_homework.domain.repository.CacheRepository
import com.example.photos_homework.domain.repository.PhotoRepository
import com.example.photos_homework.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PhotoListViewModel(
    private val repository: PhotoRepository,
    val cacheRepository: CacheRepository,
) : ViewModel() {
    val photoResult = MutableStateFlow<ResultWrapper<List<Photo>>>(ResultWrapper.None)

    fun fetchPhoto() {
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
                photoResult.emit(ResultWrapper.success(list)
                    .apply {
                        val photo = list.find { it.id == photoId }
                        photo?.let {
                            it.isFavorite = !it.isFavorite
                        }
                        println("hanz updateFavoriteStatus ${photo?.isFavorite}")
                    })
            }
        }
    }
}
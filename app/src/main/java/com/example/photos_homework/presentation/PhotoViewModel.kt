package com.example.photos_homework.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photos_homework.domain.PhotoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class PhotoViewModel(val repository: PhotoRepository) : ViewModel() {
    private val _state = MutableStateFlow(PhotoState())
    val state = _state.onStart {
        fetchPhoto()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        PhotoState()
    )

    private fun fetchPhoto() {
        TODO("Not yet implemented")
        //Fetch Photo and Update State
    }
}
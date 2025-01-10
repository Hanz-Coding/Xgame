package com.example.photos_homework.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.photos_homework.presentation.event.TopBarEvent

class TopBarViewModel : ViewModel() {
    var event by mutableStateOf<TopBarEvent>(TopBarEvent.None)
}
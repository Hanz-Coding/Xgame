package com.example.photos_homework.presentation

import com.example.photos_homework.presentation.event.TopBarEvent
import com.example.photos_homework.presentation.viewmodel.TopBarViewModel


class EventHandler(
    private val topBarViewModel: TopBarViewModel,
) {
    fun postTopBarEvent(event: TopBarEvent) {
        topBarViewModel.event = event
    }
    fun topBarEvent() = topBarViewModel.event
}

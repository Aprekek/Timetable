package ru.fevgenson.timetable.features.timetable.presentation.viewpager

sealed class PageDayUIState {
    object Loading : PageDayUIState()
    object Content : PageDayUIState()
    object Hide : PageDayUIState()
    object Empty : PageDayUIState()
}
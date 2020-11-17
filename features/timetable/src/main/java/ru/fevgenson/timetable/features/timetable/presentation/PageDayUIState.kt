package ru.fevgenson.timetable.features.timetable.presentation

sealed class PageDayUIState {
    object Loading : PageDayUIState()
    object Content : PageDayUIState()
    object Empty : PageDayUIState()
}
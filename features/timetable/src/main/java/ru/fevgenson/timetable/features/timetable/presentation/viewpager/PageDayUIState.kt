package ru.fevgenson.timetable.features.timetable.presentation.viewpager

sealed class PageDayUIState {
    object Loading : PageDayUIState()
    object FirstWeek : PageDayUIState()
    object SecondWeek : PageDayUIState()
    object Empty : PageDayUIState()
}
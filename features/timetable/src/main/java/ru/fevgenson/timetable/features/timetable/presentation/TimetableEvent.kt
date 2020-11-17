package ru.fevgenson.timetable.features.timetable.presentation

import android.os.Bundle

sealed class TimetableEvent {
    data class NavigateToCreateEvent(val bundle: Bundle) : TimetableEvent()
    data class ShowDeleteDialogEvent(val lessonId: Long) : TimetableEvent()
}
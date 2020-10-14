package ru.fevgenson.timetable.features.settings.presentation.backup

import androidx.lifecycle.ViewModel
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventsDispatcher

class SettingsBackupViewModel : ViewModel() {

    interface EventListener {
        fun navigateBack()
    }

    val eventsDispatcher = EventsDispatcher<EventListener>()

    fun onBackButtonPressed() {
        eventsDispatcher.dispatchEvent { navigateBack() }
    }
}
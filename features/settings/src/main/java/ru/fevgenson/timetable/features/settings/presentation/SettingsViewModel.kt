package ru.fevgenson.timetable.features.settings.presentation

import androidx.lifecycle.ViewModel
import ru.fevgenson.timetable.features.settings.R
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventsDispatcher

class SettingsViewModel : ViewModel() {

    interface EventListener {
        fun navigate(navigationId: Int)
    }

    val eventsDispatcher = EventsDispatcher<EventListener>()

    fun onStyleClick() {
        eventsDispatcher.dispatchEvent { navigate(R.id.navigation_from_main_to_settings_style) }
    }

    fun onNotificationsClick() {}
}
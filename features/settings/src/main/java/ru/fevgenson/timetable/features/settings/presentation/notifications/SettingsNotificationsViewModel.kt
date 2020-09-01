package ru.fevgenson.timetable.features.settings.presentation.notifications

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventsDispatcher
import ru.fevgenson.timetable.libraries.database.domain.repository.SettingsRepository

class SettingsNotificationsViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    interface EventListener {
        fun navigateBack()
    }

    val eventsDispatcher = EventsDispatcher<EventListener>()

    val foregroundServiceEnabled = MutableLiveData(settingsRepository.getForegroundServiceEnabled())
    private val foregroundServiceEnabledSaver = Observer<Boolean> {
        settingsRepository.saveForegroundServiceEnabled(it)
    }.also { foregroundServiceEnabled.observeForever(it) }

    fun onBackButtonPressed() {
        eventsDispatcher.dispatchEvent { navigateBack() }
    }

    override fun onCleared() {
        super.onCleared()
        foregroundServiceEnabled.removeObserver(foregroundServiceEnabledSaver)
    }
}
package ru.fevgenson.timetable.features.settings.presentation.notifications

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventsDispatcher
import ru.fevgenson.timetable.shared.settings.domain.usecase.GetForegroundServiceEnabledUseCase
import ru.fevgenson.timetable.shared.settings.domain.usecase.SaveForegroundServiceEnabledUseCase

class SettingsNotificationsViewModel(
    private val saveForegroundServiceEnabledUseCase: SaveForegroundServiceEnabledUseCase,
    getForegroundServiceEnabledUseCase: GetForegroundServiceEnabledUseCase
) : ViewModel() {

    interface EventListener {
        fun navigateBack()
    }

    val eventsDispatcher = EventsDispatcher<EventListener>()

    val foregroundServiceEnabled = MutableLiveData(getForegroundServiceEnabledUseCase())
    private val foregroundServiceEnabledSaver = Observer<Boolean> {
        saveForegroundServiceEnabledUseCase(it)
    }.also { foregroundServiceEnabled.observeForever(it) }

    fun onBackButtonPressed() {
        eventsDispatcher.dispatchEvent { navigateBack() }
    }

    override fun onCleared() {
        super.onCleared()
        foregroundServiceEnabled.removeObserver(foregroundServiceEnabledSaver)
    }
}
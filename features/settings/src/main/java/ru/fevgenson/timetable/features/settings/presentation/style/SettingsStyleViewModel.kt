package ru.fevgenson.timetable.features.settings.presentation.style

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventsDispatcher
import ru.fevgenson.timetable.shared.settings.domain.usecase.GetSavedThemeUseCase
import ru.fevgenson.timetable.shared.settings.domain.usecase.SaveThemeUseCase


class SettingsStyleViewModel(
    private val saveThemeUseCase: SaveThemeUseCase,
    getSavedThemeUseCase: GetSavedThemeUseCase
) : ViewModel() {

    interface EventListener {
        fun navigateBack()
    }

    val eventsDispatcher = EventsDispatcher<EventListener>()

    val selectedTheme = MutableLiveData(getSavedThemeUseCase())
    private val saveThemeObserver = Observer<Int> {
        saveThemeUseCase(it)
    }.also { selectedTheme.observeForever(it) }

    fun onBackButtonPressed() {
        eventsDispatcher.dispatchEvent { navigateBack() }
    }

    override fun onCleared() {
        super.onCleared()
        selectedTheme.removeObserver(saveThemeObserver)
    }
}
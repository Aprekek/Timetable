package ru.fevgenson.timetable.features.settings.presentation.style

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ru.fevgenson.timetable.libraries.database.domain.repository.SettingsRepository

class SettingsStyleViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    val selectedTheme = MutableLiveData(settingsRepository.getSavedTheme())
    private val saveThemeObserver = Observer<Int> {
        settingsRepository.saveTheme(it)
    }.also { selectedTheme.observeForever(it) }

    override fun onCleared() {
        super.onCleared()
        selectedTheme.removeObserver(saveThemeObserver)
    }
}
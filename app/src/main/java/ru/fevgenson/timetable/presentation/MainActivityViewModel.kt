package ru.fevgenson.timetable.presentation

import androidx.lifecycle.ViewModel
import ru.fevgenson.timetable.shared.settings.domain.usecase.GetForegroundServiceEnabledUseCase
import ru.fevgenson.timetable.shared.settings.domain.usecase.GetSavedThemeUseCase

class MainActivityViewModel(
    private val getForegroundServiceEnabledUseCase: GetForegroundServiceEnabledUseCase,
    private val getSavedThemeUseCase: GetSavedThemeUseCase
) : ViewModel() {

    val foregroundServiceEnabled: Boolean
        get() = getForegroundServiceEnabledUseCase()

    val savedTheme: Int
        get() = getSavedThemeUseCase()
}
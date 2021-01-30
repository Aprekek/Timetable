package ru.fevgenson.timetable.shared.settings.domain.usecase

import ru.fevgenson.timetable.shared.settings.domain.repository.SettingsRepository

class GetSavedThemeUseCase(
    private val repository: SettingsRepository
) {

    operator fun invoke(): Int = repository.getSavedTheme()
}
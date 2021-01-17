package ru.fevgenson.timetable.shared.settings.domain.usecase

import ru.fevgenson.timetable.shared.settings.domain.repository.SettingsRepository

class SaveThemeUseCase(
    private val repository: SettingsRepository
) {

    operator fun invoke(theme: Int) {
        repository.saveTheme(theme)
    }
}
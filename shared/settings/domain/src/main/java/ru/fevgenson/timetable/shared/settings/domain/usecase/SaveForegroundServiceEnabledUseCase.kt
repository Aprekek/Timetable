package ru.fevgenson.timetable.shared.settings.domain.usecase

import ru.fevgenson.timetable.shared.settings.domain.repository.SettingsRepository

class SaveForegroundServiceEnabledUseCase(
    private val repository: SettingsRepository
) {

    operator fun invoke(enabled: Boolean) {
        repository.saveForegroundServiceEnabled(enabled)
    }
}
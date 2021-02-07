package ru.fevgenson.timetable.shared.settings.domain.usecase

import ru.fevgenson.timetable.shared.settings.domain.repository.SettingsRepository

class GetTimeBaseNotificationsEnabledUseCase(
    private val repository: SettingsRepository
) {

    operator fun invoke(): Boolean = repository.getTimeBaseNotificationsEnabled()
}
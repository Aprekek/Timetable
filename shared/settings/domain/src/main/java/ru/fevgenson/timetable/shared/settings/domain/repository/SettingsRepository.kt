package ru.fevgenson.timetable.shared.settings.domain.repository

interface SettingsRepository {

    fun getSavedTheme(): Int
    fun saveTheme(theme: Int)

    fun getForegroundServiceEnabled(): Boolean
    fun saveForegroundServiceEnabled(enabled: Boolean)

    fun getTimeBaseNotificationsEnabled(): Boolean
    fun saveTimeBaseNotificationsEnabled(enabled: Boolean)
}
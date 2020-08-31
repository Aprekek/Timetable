package ru.fevgenson.timetable.libraries.database.domain.repository

interface SettingsRepository {
    fun getSavedTheme(): Int
    fun saveTheme(theme: Int)
    fun getForegroundServiceEnabled(): Boolean
    fun saveForegroundServiceEnabled(enabled: Boolean)
}
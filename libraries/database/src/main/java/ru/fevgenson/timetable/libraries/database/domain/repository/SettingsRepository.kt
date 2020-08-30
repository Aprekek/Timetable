package ru.fevgenson.timetable.libraries.database.domain.repository

interface SettingsRepository {
    fun getSavedTheme(): Int
    fun saveTheme(theme: Int)
}
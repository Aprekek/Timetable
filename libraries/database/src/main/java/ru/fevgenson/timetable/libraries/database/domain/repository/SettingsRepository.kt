package ru.fevgenson.timetable.libraries.database.domain.repository

interface SettingsRepository {

    companion object {
        const val DARK = 0
        const val LIGHT = 1
        const val SYSTEM = 2
    }

    fun getSavedTheme(): Int
    fun saveTheme(theme: Int)
}
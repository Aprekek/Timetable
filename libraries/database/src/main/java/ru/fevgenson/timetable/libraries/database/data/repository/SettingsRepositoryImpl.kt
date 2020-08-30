package ru.fevgenson.timetable.libraries.database.data.repository

import android.content.SharedPreferences
import ru.fevgenson.timetable.libraries.database.domain.repository.SettingsRepository

internal class SettingsRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : SettingsRepository {

    companion object {

        const val NAME = "SETTINGS"

        private const val THEME = "THEME"
    }

    override fun getSavedTheme(): Int = sharedPreferences.getInt(THEME, SettingsRepository.SYSTEM)

    override fun saveTheme(theme: Int) {
        sharedPreferences.edit().putInt(THEME, theme).apply()
    }
}
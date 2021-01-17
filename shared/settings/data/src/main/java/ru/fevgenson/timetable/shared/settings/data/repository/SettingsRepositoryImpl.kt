package ru.fevgenson.timetable.shared.settings.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import ru.fevgenson.timetable.shared.settings.domain.repository.SettingsRepository

internal class SettingsRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : SettingsRepository {

    companion object {

        private const val NAME = "SETTINGS"

        private const val THEME = "THEME"
        private const val FOREGROUND_SERVICE = "FOREGROUND_SERVICE"

        fun getSharedPreferences(context: Context) =
            context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    override fun getSavedTheme(): Int =
        sharedPreferences.getInt(THEME, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

    override fun saveTheme(theme: Int) {
        sharedPreferences.edit().putInt(THEME, theme).apply()
    }

    override fun getForegroundServiceEnabled(): Boolean =
        sharedPreferences.getBoolean(FOREGROUND_SERVICE, false)

    override fun saveForegroundServiceEnabled(enabled: Boolean) {
        sharedPreferences.edit().putBoolean(FOREGROUND_SERVICE, enabled).apply()
    }
}
package ru.fevgenson.timetable.features.settings.presentation.style

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsStyleViewModel : ViewModel() {

    enum class Themes {
        light,
        night,
        system
    }

    val selectedTheme = MutableLiveData<Themes>()
}
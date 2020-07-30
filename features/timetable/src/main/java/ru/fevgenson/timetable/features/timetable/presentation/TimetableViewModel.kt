package ru.fevgenson.timetable.features.timetable.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.fevgenson.timetable.libraries.core.dateutils.DateUtils

class TimetableViewModel : ViewModel() {

    val selectedWeekLiveData = MutableLiveData(DateUtils.getCurrentWeek())
    val selectedDayLiveData = MutableLiveData(DateUtils.getCurrentDay())
}
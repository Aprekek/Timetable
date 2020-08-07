package ru.fevgenson.timetable.features.timetable.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventsDispatcher
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils

class TimetableViewModel : ViewModel() {

    interface EventListener {
        fun navigateToCreate()
    }

    val selectedWeekLiveData = MutableLiveData(DateUtils.getCurrentWeek())
    val selectedDayLiveData = MutableLiveData(DateUtils.getCurrentDay())

    val eventsDispatcher = EventsDispatcher<EventListener>()

    fun onCreateLessonButtonClick() {
        eventsDispatcher.dispatchEvent { navigateToCreate() }
    }
}
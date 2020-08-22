package ru.fevgenson.timetable.features.timetable.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.get
import ru.fevgenson.timetable.features.timetable.presentation.viewpager.PageDayViewModel
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventsDispatcher
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils

class TimetableViewModel : ViewModel() {

    interface EventListener {
        fun navigateToCreate(weekType: Int, day: Int)
    }

    val selectedWeekLiveData = MutableLiveData(DateUtils.getCurrentWeek())
    val selectedDayLiveData = MutableLiveData(DateUtils.getCurrentDay())

    val eventsDispatcher = EventsDispatcher<EventListener>()

    val dayViewModelsList = List(DateUtils.WEEK_DAYS) {
        get(PageDayViewModel::class.java) {
            parametersOf(it, this)
        }
    }

    fun onCreateLessonButtonClick() {
        eventsDispatcher.dispatchEvent {
            navigateToCreate(
                weekType = selectedWeekLiveData.value
                    ?: throw IllegalStateException("wrong week constant"),
                day = selectedDayLiveData.value
                    ?: throw IllegalStateException("wrong day constant")
            )
        }
    }
}
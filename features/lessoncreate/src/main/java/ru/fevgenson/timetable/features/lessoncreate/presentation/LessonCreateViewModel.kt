package ru.fevgenson.timetable.features.lessoncreate.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventsDispatcher
import ru.fevgenson.timetable.libraries.core.utils.timeutils.MyTimeUtils

class LessonCreateViewModel : ViewModel() {

    interface EventListener {
        fun navigateToTimetable()
        fun closeKeyboard()
        fun setTimeAndInvokeTimePicker(timeBorder: MyTimeUtils.TimeBorders)
    }

    companion object {
        const val MAIN_PAGE = 0
        const val LOCATION_AND_TYPE_PAGE = 1
        const val TEACHER_PAGE = 2

        const val PAGES_COUNT = 3

        const val LESSON_LENGTH_MIN = 95
        const val MINUTES_IN_DAY = 1440
    }

    val subject = MutableLiveData<String>("")
    val housing = MutableLiveData<String>("")
    val classroom = MutableLiveData<String>("")
    val type = MutableLiveData<String>("")
    val teachersName = MutableLiveData<String>("")
    val email = MutableLiveData<String>("")
    val phone = MutableLiveData<String>("")

    val timeStartMinutes = MutableLiveData<Int?>(null)
    val timeEndMinutes = MutableLiveData<Int?>(null)
    val timeStartString: LiveData<String> = Transformations.map(timeStartMinutes) {
        convertTimeToString(it)
    }
    val timeEndString: LiveData<String> = Transformations.map(timeEndMinutes) {
        convertTimeToString(it)
    }

    init {
        timeStartMinutes.observeForever { timeStartMinutes ->
            if (timeEndMinutes.value == null && timeStartMinutes != null) {
                timeEndMinutes.value = (timeStartMinutes + LESSON_LENGTH_MIN).rem(MINUTES_IN_DAY)
            }
        }
        timeEndMinutes.observeForever { timeEndMinutes ->
            if (timeStartMinutes.value == null && timeEndMinutes != null) {
                timeStartMinutes.value = if (timeEndMinutes >= LESSON_LENGTH_MIN) {
                    timeEndMinutes - LESSON_LENGTH_MIN
                } else {
                    MINUTES_IN_DAY + timeEndMinutes - LESSON_LENGTH_MIN
                }
            }
        }
    }

    val eventsDispatcher = EventsDispatcher<EventListener>()

    private val _currentPage: MutableLiveData<Int> = MutableLiveData(0)
    val currentPage: LiveData<Int>
        get() = _currentPage

    fun onTopBackButtonClick() {
        eventsDispatcher.dispatchEvent { closeKeyboard() }
        if (_currentPage.value == MAIN_PAGE) {
            eventsDispatcher.dispatchEvent { navigateToTimetable() }
        } else {
            _currentPage.value = currentPage.value?.minus(1)
        }
    }

    fun onNextButtonClick() {
        eventsDispatcher.dispatchEvent { closeKeyboard() }
        if (_currentPage.value == TEACHER_PAGE) {
            eventsDispatcher.dispatchEvent { navigateToTimetable() }
        } else {
            _currentPage.value = currentPage.value?.plus(1)
        }
    }

    fun onTimeSetButtonClick(timeBound: MyTimeUtils.TimeBorders) {
        eventsDispatcher.dispatchEvent { setTimeAndInvokeTimePicker(timeBound) }
    }

    fun onDoneTimePickerSetTime(ours: Int, min: Int, timeBound: MyTimeUtils.TimeBorders) {
        if (timeBound == MyTimeUtils.TimeBorders.START) {
            timeStartMinutes.value = ours * 60 + min
        } else {
            timeEndMinutes.value = ours * 60 + min
        }
    }

    private fun convertTimeToString(time: Int?): String {
        return if (time != null) {
            MyTimeUtils.convertTimeInMinutesToString(time)
        } else {
//            R.string.lesson_create_button_time_not_set
            "-- : --"
        }
    }

    fun onClearItemClick() {
        Log.d("LessonCreateViewModel", "Button was clicked")
    }
}
package ru.fevgenson.timetable.features.lessoncreate.presentation

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventsDispatcher

class LessonCreateViewModel : ViewModel() {

    interface EventListener {
        fun navigateToTimetable()
        fun closeKeyboard()
        fun setTimeAndInvokeTimePicker()
    }

    companion object {
        const val MAIN_PAGE = 0
        const val LOCATION_AND_TYPE_PAGE = 1
        const val TEACHER_PAGE = 2

        const val PAGES_COUNT = 3

        const val LESSON_LENGTH_MIN = 95
        const val MINUTES_IN_DAY = 1440
    }

    //Temporarily |----
    var isBegin: Boolean? = null
    var timeStartMin: Int? = null
    var timeEndMin: Int? = null
    var timeStartString = ObservableField<String>("")
    var timeEndString = ObservableField<String>("")
    //----|

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

    fun onTimeSetButtonClick(isBegin: Boolean) {
        this.isBegin = isBegin
        eventsDispatcher.dispatchEvent { setTimeAndInvokeTimePicker() }
    }

    fun onClearItemClick() {
        Log.d("LessonCreateViewModel", "Button was clicked")
    }
}
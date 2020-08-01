package ru.fevgenson.timetable.features.lessoncreate.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.fevgenson.timetable.features.lessoncreate.utills.LessonCreatePages
import ru.fevgenson.timetable.libraries.core.presentation.eventutils.EventsDispatcher

class LessonCreateViewModel : ViewModel() {

    interface EventListener {
        fun navigateToTimetable()
    }

    val eventsDispatcher = EventsDispatcher<EventListener>()

    private val _currentPage: MutableLiveData<Int> = MutableLiveData(0)
    val currentPage: LiveData<Int>
        get() = _currentPage

    fun onTopBackButtonClick() {
        if (_currentPage.value == LessonCreatePages.MAIN_PAGE) {
            eventsDispatcher.dispatchEvent { navigateToTimetable() }
        } else {
            _currentPage.value = currentPage.value?.minus(1)
        }
    }

    fun onNextButtonClick() {
        if (_currentPage.value == LessonCreatePages.TEACHER_PAGE) {
            eventsDispatcher.dispatchEvent { navigateToTimetable() }
        } else {
            _currentPage.value = currentPage.value?.plus(1)
        }
    }

    fun onClearItemClick() {
        Log.d("LessonCreateViewModel", "Button was clicked")
    }
}
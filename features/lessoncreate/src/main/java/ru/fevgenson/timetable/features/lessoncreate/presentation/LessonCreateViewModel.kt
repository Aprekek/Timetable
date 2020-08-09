package ru.fevgenson.timetable.features.lessoncreate.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.fevgenson.timetable.features.lessoncreate.R
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventsDispatcher
import ru.fevgenson.timetable.libraries.core.presentation.utils.timeutils.MyTimeUtils
import ru.fevgenson.timetable.libraries.core.providers.ResourceProvider

class LessonCreateViewModel(private val resourceProvider: ResourceProvider) : ViewModel() {

    interface EventListener {
        fun navigateToTimetable(action: Int)
        fun closeKeyboard()
        fun setTimeAndInvokeTimePicker(timeBorder: MyTimeUtils.TimeBorders)
        fun onValidationFailed()
        fun showDialog()
    }

    companion object {
        const val MAIN_PAGE = 0
        const val LOCATION_AND_TYPE_PAGE = 1
        const val TEACHER_PAGE = 2

        const val PAGES_COUNT = 3

        const val LESSON_LENGTH_MIN = 95
        const val MINUTES_IN_DAY = 1440

        const val ACTION_DONE = 0
        const val ACTION_CANCEL = 1
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

    val toolbarTitle: LiveData<String> = Transformations.map(currentPage) { page ->
        return@map when (page) {
            MAIN_PAGE -> resourceProvider.getStringById(R.string.lesson_create_title_main_info)
            LOCATION_AND_TYPE_PAGE -> resourceProvider.getStringById(R.string.lesson_create_title_location_and_type)
            TEACHER_PAGE -> resourceProvider.getStringById(R.string.lesson_create_title_teacher)
            else -> throw IllegalStateException("Page $page not found")
        }
    }

    fun onTopBackButtonClick() {
        eventsDispatcher.dispatchEvent { closeKeyboard() }
        if (_currentPage.value == MAIN_PAGE) {
            onCancel()
        } else {
            _currentPage.value = currentPage.value?.minus(1)
        }
    }

    fun onNextButtonClick() {
        eventsDispatcher.dispatchEvent { closeKeyboard() }
        if (_currentPage.value == TEACHER_PAGE) {
            onDone()
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
            resourceProvider.getStringById(R.string.lesson_create_button_time_not_set)
        }
    }

    fun onClearItemClick() {
        Log.d("LessonCreateViewModel", "Button was clicked")
    }

    fun onCancelButtonClick() {
        //onCancel()
        eventsDispatcher.dispatchEvent { showDialog() }
    }

    private fun onCancel() {
        eventsDispatcher.dispatchEvent { navigateToTimetable(ACTION_CANCEL) }
    }

    fun onDoneButtonClick() {
        if (validation())
            onDone()
        else {
            _currentPage.value = MAIN_PAGE
            eventsDispatcher.dispatchEvent { onValidationFailed() }
        }
    }

    private fun onDone() {
        eventsDispatcher.dispatchEvent { navigateToTimetable(ACTION_DONE) }
    }

    //Написать проверку для chips
    private fun validation(): Boolean {
        if (subject.value?.isEmpty() == true)
            return false

        val timeNotSet = resourceProvider.getStringById(R.string.lesson_create_button_time_not_set)
        if (timeStartString.value?.equals(timeNotSet) == true)
            return false
        if (timeEndString.value?.equals(timeNotSet) == true)
            return false

        return true
    }
}
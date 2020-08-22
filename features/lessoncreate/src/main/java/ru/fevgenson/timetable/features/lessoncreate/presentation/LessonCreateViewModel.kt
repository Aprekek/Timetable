package ru.fevgenson.timetable.features.lessoncreate.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.fevgenson.timetable.features.lessoncreate.R
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventsDispatcher
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils
import ru.fevgenson.timetable.libraries.core.utils.dateutils.MyTimeUtils

class LessonCreateViewModel(
    weekType: Int,
    day: Int
) : ViewModel() {

    interface EventListener {
        fun navigateToTimetable()
        fun closeKeyboard()
        fun setTimeAndInvokeTimePicker(timeBorder: MyTimeUtils.TimeBorders)
        fun showPopupMessage(message: Int)
        fun showDialog(title: Int, description: Int, action: Int)
    }

    companion object {
        const val MAIN_PAGE = 0
        const val LOCATION_AND_TYPE_PAGE = 1
        const val TEACHER_PAGE = 2

        const val PAGES_COUNT = 3

        private const val LESSON_LENGTH_MIN = 95
        private const val MINUTES_IN_DAY = 1440

        private const val ACTION_DONE = 0
        const val ACTION_CANCEL = 1

        private const val TIME_NOT_SET_STRING = "-- : --"
    }

    val subject = MutableLiveData<String>()
    val subjectError: LiveData<Int?> = Transformations.map(subject) {
        if (it.isNullOrEmpty()) R.string.lesson_create_edit_text_error_message else null
    }
    val housing = MutableLiveData<String>()
    val classroom = MutableLiveData<String>()
    val type = MutableLiveData<String>()
    val teachersName = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val phone = MutableLiveData<String>()

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

    val toolbarTitle: LiveData<Int> = Transformations.map(currentPage) { page ->
        when (page) {
            MAIN_PAGE -> R.string.lesson_create_title_main_info
            LOCATION_AND_TYPE_PAGE -> R.string.lesson_create_title_location_and_type
            TEACHER_PAGE -> R.string.lesson_create_title_teacher
            else -> throw IllegalStateException("Page $page not found")
        }
    }

    val firstWeekChips = MutableLiveData<List<Boolean>>(
        MutableList(DateUtils.WEEK_DAYS) { false }.apply {
            if (weekType == DateUtils.FIRST_WEEK) {
                set(day, true)
            }
        }
    )

    val secondWeekChips = MutableLiveData<List<Boolean>>(
        MutableList(DateUtils.WEEK_DAYS) { false }.apply {
            if (weekType == DateUtils.SECOND_WEEK) {
                set(day, true)
            }
        }
    )

    fun onBackButtonClick() {
        eventsDispatcher.dispatchEvent { closeKeyboard() }
        if (_currentPage.value == MAIN_PAGE) {
            onCancelButtonClick()
        } else {
            _currentPage.value = currentPage.value?.minus(1)
        }
    }

    fun onNextButtonClick() {
        eventsDispatcher.dispatchEvent { closeKeyboard() }
        if (_currentPage.value == TEACHER_PAGE) {
            onDoneButtonClick()
        } else {
            _currentPage.value = currentPage.value?.plus(1)
        }
    }

    fun onTimeSetButtonClick(timeBound: MyTimeUtils.TimeBorders) {
        eventsDispatcher.dispatchEvent { setTimeAndInvokeTimePicker(timeBound) }
    }

    fun onDoneTimePickerSetTime(hours: Int, min: Int, timeBound: MyTimeUtils.TimeBorders) {
        if (timeBound == MyTimeUtils.TimeBorders.START) {
            timeStartMinutes.value = hours * MyTimeUtils.MINUTES_IN_HOUR + min
        } else {
            timeEndMinutes.value = hours * MyTimeUtils.MINUTES_IN_HOUR + min
        }
    }

    fun onClearItemClick() {
        when (_currentPage.value) {
            MAIN_PAGE -> {
                subject.value = null
                timeStartMinutes.value = null
                timeEndMinutes.value = null
                firstWeekChips.value = List(DateUtils.WEEK_DAYS) { false }
                secondWeekChips.value = List(DateUtils.WEEK_DAYS) { false }
            }
            LOCATION_AND_TYPE_PAGE -> {
                housing.value = null
                classroom.value = null
                type.value = null
            }
            TEACHER_PAGE -> {
                teachersName.value = null
                email.value = null
                phone.value = null
            }
        }

        eventsDispatcher.dispatchEvent {
            showPopupMessage(R.string.lesson_create_clear_message)
        }
    }

    fun onCancelButtonClick() {
        eventsDispatcher.dispatchEvent {
            showDialog(
                R.string.lesson_create_cancel_dialog_title,
                R.string.lesson_create_cancel_dialog_description,
                ACTION_CANCEL
            )
        }
    }

    private fun onCancel() {
        eventsDispatcher.dispatchEvent { navigateToTimetable() }
    }

    fun onDoneButtonClick() {
        if (isDataValid())
            eventsDispatcher.dispatchEvent {
                showDialog(
                    R.string.lesson_create_done_dialog_title,
                    R.string.lesson_create_done_dialog_description,
                    ACTION_DONE
                )
            }
        else {
            subject.value = subject.value
            _currentPage.value = MAIN_PAGE
            eventsDispatcher.dispatchEvent {
                showPopupMessage(
                    R.string.lesson_create_fields_filling_request
                )
            }
        }
    }

    private fun onDone() {
        eventsDispatcher.dispatchEvent { navigateToTimetable() }
    }

    fun onDialogResult(action: Int) {
        if (action == ACTION_DONE)
            onDone()
        else
            onCancel()
    }

    private fun isDataValid() = subjectError.value == null &&
            timeStartMinutes.value != null &&
            (firstWeekChips.value?.find { it } != null || secondWeekChips.value?.find { it } != null)

    private fun convertTimeToString(time: Int?): String = time?.let {
        MyTimeUtils.convertTimeInMinutesToString(it)
    } ?: TIME_NOT_SET_STRING

}
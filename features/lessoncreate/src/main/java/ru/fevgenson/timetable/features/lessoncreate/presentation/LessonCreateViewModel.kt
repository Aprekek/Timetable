package ru.fevgenson.timetable.features.lessoncreate.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.fevgenson.libraries.navigation.di.NavigationConstants
import ru.fevgenson.timetable.features.lessoncreate.R
import ru.fevgenson.timetable.features.lessoncreate.domain.usecase.*
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventsDispatcher
import ru.fevgenson.timetable.shared.lesson.domain.entity.LessonEntity
import ru.fevgenson.timetable.shared.lesson.domain.entity.TeacherEntity
import ru.fevgenson.timetable.shared.lesson.domain.usecase.GetAllTeachersUseCase
import ru.fevgenson.timetable.shared.lesson.domain.usecase.GetLessonByIdUseCase
import ru.fevgenson.timetable.shared.lesson.domain.usecase.SaveLessonsUseCase
import ru.fevgenson.timetable.shared.timeutils.domain.constants.WeekTypes
import ru.fevgenson.timetable.shared.timeutils.domain.formatter.TimeFormatter
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentTimeUseCase


class LessonCreateViewModel(
    weekType: Int,
    day: Int,
    private val id: Long,
    private val openType: Int,
    private val getClassroomsValuesUseCase: GetClassroomsValuesUseCase,
    private val getHousingsValuesUseCase: GetHousingsValuesUseCase,
    private val getSubjectsValuesUseCase: GetSubjectsValuesUseCase,
    private val getTimesValuesUseCase: GetTimesValuesUseCase,
    private val getTypesValuesUseCase: GetTypesValuesUseCase,
    private val getAllTeachersUseCase: GetAllTeachersUseCase,
    private val getLessonByIdUseCase: GetLessonByIdUseCase,
    private val saveLessonsUseCase: SaveLessonsUseCase,
    private val getCurrentTimeUseCase: GetCurrentTimeUseCase,
    private val timeFormatter: TimeFormatter
) : ViewModel() {

    interface EventListener {
        fun navigateToTimetable()
        fun closeKeyboard()
        fun requestTime(
            timeBorder: TimeFormatter.TimeBorders,
            hoursOnStart: Int,
            minOnStart: Int
        )

        fun showPopupMessage(message: Int)
        fun showDialog(title: Int, description: Int, action: Int)
        fun showDialog(title: Int, content: List<String>)
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

        private const val DAYS_IN_WEEK = 7
    }

    val subject = MutableStateFlow<String?>(null)
    val subjectAutoComplete = flow { emit(getSubjectsValuesUseCase()) }
    val subjectError = subject.map {
        if (it?.isBlank() == true) R.string.lesson_create_edit_text_error_message else null
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    val housing = MutableStateFlow<String?>(null)
    val housingAutoComplete = flow { emit(getHousingsValuesUseCase()) }

    val classroom = MutableStateFlow<String?>(null)
    val classroomAutoComplete = flow { emit(getClassroomsValuesUseCase()) }

    val type = MutableStateFlow<String?>(null)
    val typeAutocomplete = flow { emit(getTypesValuesUseCase()) }

    val teachersName = MutableStateFlow<String?>(null)
    val teacherAutocomplete = flow { emit(getAllTeachersUseCase()) }

    val email = MutableStateFlow<String?>(null).also { emailFlow ->
        teachersName.combine(teacherAutocomplete) { teachersName: String?, teacherAutocomplete: List<TeacherEntity> ->
            if (emailFlow.value.isNullOrBlank()) {
                teacherAutocomplete.find { it.name == teachersName }?.let {
                    emailFlow.value = it.email
                }
            }
        }.launchIn(viewModelScope)
    }

    val phone = MutableStateFlow<String?>(null).also { phoneFlow ->
        teachersName.combine(teacherAutocomplete) { teachersName: String?, teacherAutocomplete: List<TeacherEntity> ->
            if (phoneFlow.value.isNullOrBlank()) {
                teacherAutocomplete.find { it.name == teachersName }?.let {
                    phoneFlow.value = it.phone
                }
            }
        }.launchIn(viewModelScope)
    }

    private val timeStartMinutes = MutableStateFlow<Int?>(null)
    private val timeEndMinutes = MutableStateFlow<Int?>(null)

    val timeStartString = timeStartMinutes.map {
        convertTimeToString(it)
    }.stateIn(viewModelScope, SharingStarted.Lazily, TIME_NOT_SET_STRING)
    val timeEndString = timeEndMinutes.map {
        convertTimeToString(it)
    }.stateIn(viewModelScope, SharingStarted.Lazily, TIME_NOT_SET_STRING)

    val timeAutoComplete = flow {
        emit(getTimesValuesUseCase())
    }.stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    val eventsDispatcher = EventsDispatcher<EventListener>()

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int>
        get() = _currentPage

    val toolbarTitle = currentPage.map { page ->
        when (page) {
            MAIN_PAGE -> R.string.lesson_create_title_main_info
            LOCATION_AND_TYPE_PAGE -> R.string.lesson_create_title_location_and_type
            TEACHER_PAGE -> R.string.lesson_create_title_teacher
            else -> throw IllegalStateException("Page $page not found")
        }
    }

    val firstWeekChips = MutableStateFlow<List<Boolean>>(
        MutableList(DAYS_IN_WEEK) { false }.apply {
            if (weekType == WeekTypes.FIRST_WEEK) {
                set(day, true)
            }
        }
    )

    val secondWeekChips = MutableStateFlow<List<Boolean>>(
        MutableList(DAYS_IN_WEEK) { false }.apply {
            if (weekType == WeekTypes.SECOND_WEEK) {
                set(day, true)
            }
        }
    )

    init {
        initTime()
        initOpenType()
    }

    private fun initOpenType() {
        if (openType == NavigationConstants.LessonCreate.COPY ||
            openType == NavigationConstants.LessonCreate.EDIT
        ) {
            loadLesson()
        }
    }

    private fun initTime() {
        timeStartMinutes.onEach { timeStartMinutes ->
            if (timeEndMinutes.value == null && timeStartMinutes != null) {
                timeEndMinutes.value = (timeStartMinutes + LESSON_LENGTH_MIN).rem(MINUTES_IN_DAY)
            }
        }.launchIn(viewModelScope)
        timeEndMinutes.onEach { timeEndMinutes ->
            if (timeStartMinutes.value == null && timeEndMinutes != null) {
                timeStartMinutes.value = if (timeEndMinutes >= LESSON_LENGTH_MIN) {
                    timeEndMinutes - LESSON_LENGTH_MIN
                } else {
                    MINUTES_IN_DAY + timeEndMinutes - LESSON_LENGTH_MIN
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadLesson() {
        viewModelScope.launch {
            getLessonByIdUseCase(id).let {
                subject.value = it.subject
                housing.value = it.housing
                classroom.value = it.classroom
                type.value = it.type
                teachersName.value = it.teacher?.name
                email.value = it.teacher?.email
                phone.value = it.teacher?.phone
                timeStartMinutes.value =
                    timeFormatter.getMinutes(it.time, TimeFormatter.TimeBorders.START)
                timeEndMinutes.value =
                    timeFormatter.getMinutes(it.time, TimeFormatter.TimeBorders.END)
                when (it.weekType) {
                    WeekTypes.FIRST_WEEK -> firstWeekChips.value =
                        firstWeekChips.value.toMutableList().apply { set(it.day, true) }
                    WeekTypes.SECOND_WEEK -> secondWeekChips.value =
                        secondWeekChips.value.toMutableList().apply { set(it.day, true) }
                }
            }
        }
    }

    private fun saveChanges() {
        viewModelScope.launch {
            val lessons = mutableListOf<LessonEntity>()
            firstWeekChips.value.forEachIndexed { day, checked ->
                if (checked) {
                    lessons.add(createLesson(day, WeekTypes.FIRST_WEEK))
                }
            }
            secondWeekChips.value.forEachIndexed { day, checked ->
                if (checked) {
                    lessons.add(createLesson(day, WeekTypes.SECOND_WEEK))
                }
            }
            if (openType == NavigationConstants.LessonCreate.EDIT) {
                lessons.first().id = id
            }
            saveLessonsUseCase(*lessons.toTypedArray())
            close()
        }
    }

    private fun createLesson(day: Int, weekType: Int) = LessonEntity(
        subject = requireNotNull(subject.value) { "subject cant be null" },
        time = timeFormatter.format(timeStartString.value, timeEndString.value),
        day = day,
        weekType = weekType,
        housing = housing.value?.takeIf { it.isNotBlank() }?.trim(),
        classroom = classroom.value?.takeIf { it.isNotBlank() }?.trim(),
        type = type.value?.takeIf { it.isNotBlank() }?.trim(),
        teacher = teachersName.value?.let { teacherName ->
            TeacherEntity(
                id = id,
                name = teacherName,
                phone = phone.value?.takeIf { it.isNotBlank() },
                email = email.value?.takeIf { it.isNotBlank() }?.trim()
            )
        }
    )

    fun onBackButtonClick() {
        eventsDispatcher.dispatchEvent { closeKeyboard() }
        if (_currentPage.value == MAIN_PAGE) {
            onCancelButtonClick()
        } else {
            _currentPage.value--
        }
    }

    fun onNextButtonClick() {
        eventsDispatcher.dispatchEvent { closeKeyboard() }
        if (_currentPage.value == TEACHER_PAGE) {
            onDoneButtonClick()
        } else {
            _currentPage.value++
        }
    }

    fun onTimeSetButtonClick(timeBound: TimeFormatter.TimeBorders) {
        val minutes = if (timeBound == TimeFormatter.TimeBorders.START) {
            timeStartMinutes.value
        } else {
            timeEndMinutes.value
        } ?: getCurrentTimeUseCase()
        eventsDispatcher.dispatchEvent {
            requestTime(
                timeBorder = timeBound,
                hoursOnStart = timeFormatter.getHours(minutes),
                minOnStart = timeFormatter.getMinutesWithoutHours(minutes)
            )
        }
    }

    fun onDoneTimePickerSetTime(hours: Int, min: Int, timeBound: TimeFormatter.TimeBorders) {
        if (timeBound == TimeFormatter.TimeBorders.START) {
            timeStartMinutes.value = timeFormatter.getMinutes(hours, min)
        } else {
            timeEndMinutes.value = timeFormatter.getMinutes(hours, min)
        }
    }

    fun onAutocompleteTimeButtonClick() {
        eventsDispatcher.dispatchEvent {
            showDialog(
                R.string.lesson_create_time_autocomplete_dialog_title,
                timeAutoComplete.value
            )
        }
    }

    fun onClearItemClick() {
        when (_currentPage.value) {
            MAIN_PAGE -> {
                subject.value = ""
                timeStartMinutes.value = null
                timeEndMinutes.value = null
                firstWeekChips.value = List(DAYS_IN_WEEK) { false }
                secondWeekChips.value = List(DAYS_IN_WEEK) { false }
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

    private fun close() {
        eventsDispatcher.dispatchEvent { navigateToTimetable() }
    }

    fun onDialogResult(action: Int) {
        if (action == ACTION_DONE) {
            saveChanges()
        } else {
            onCancel()
        }
    }

    fun onDialogResult(time: String) {
        timeStartMinutes.value = timeFormatter.getMinutes(time, TimeFormatter.TimeBorders.START)
        timeEndMinutes.value = timeFormatter.getMinutes(time, TimeFormatter.TimeBorders.END)
    }

    private fun isDataValid() = subjectError.value == null &&
            timeStartMinutes.value != null &&
            (firstWeekChips.value.find { it } != null || secondWeekChips.value.find { it } != null)

    private fun convertTimeToString(time: Int?): String =
        time?.let(timeFormatter::format) ?: TIME_NOT_SET_STRING
}
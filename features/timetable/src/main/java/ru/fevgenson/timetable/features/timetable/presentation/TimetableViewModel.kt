package ru.fevgenson.timetable.features.timetable.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.fevgenson.libraries.navigation.di.NavigationConstants.LessonCreate
import ru.fevgenson.timetable.features.timetable.domain.usecase.GetLessonsUseCase
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventLiveData
import ru.fevgenson.timetable.shared.lesson.domain.usecase.DeleteLessonsUseCase
import ru.fevgenson.timetable.shared.timeutils.domain.entity.WeekAndDayPair
import ru.fevgenson.timetable.shared.timeutils.domain.scenario.GetWeekDatesScenario
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentDayUseCase
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentWeekTypeUseCase

@ExperimentalCoroutinesApi
class TimetableViewModel(
    private val deleteLessonsUseCase: DeleteLessonsUseCase,
    private val getWeekDatesScenario: GetWeekDatesScenario,
    getCurrentWeekTypeUseCase: GetCurrentWeekTypeUseCase,
    getCurrentDayUseCase: GetCurrentDayUseCase,
    getLessonsUseCase: GetLessonsUseCase,
    currentDayAndWeek: Flow<WeekAndDayPair>
) : ViewModel() {

    companion object {

        const val DAYS_IN_WEEK = 7
    }

    val currentDay = currentDayAndWeek.map { it.day }
    val currentWeekType = currentDayAndWeek.map { it.weekType }

    val eventLiveData = EventLiveData<TimetableEvent>()

    val selectedWeekType = MutableStateFlow(getCurrentWeekTypeUseCase())
    val selectedDay = MutableStateFlow(getCurrentDayUseCase())

    val selectedWeekTypeDates =
        selectedWeekType.combine(currentWeekType) { selected: Int, _: Int ->
            getWeekDatesScenario(selected)
        }

    val selectedWeekTypeIsCurrentWeekType =
        selectedWeekType.combine(currentWeekType) { selected: Int, current: Int ->
            selected == current
        }

    val dayViewModelsList = List(DAYS_IN_WEEK) {
        PageDayViewModelDelegate(
            parentViewModel = this,
            coroutineScope = viewModelScope,
            currentDay = it,
            getLessonsUseCase = getLessonsUseCase
        )
    }

    fun onCreateLessonButtonClick() {
        eventLiveData.dispatchEvent(
            TimetableEvent.NavigateToCreateEvent(
                Bundle().apply {
                    putInt(LessonCreate.DAY, selectedDay.value)
                    putInt(LessonCreate.WEEK_TYPE, selectedWeekType.value)
                    putInt(LessonCreate.OPEN_TYPE, LessonCreate.CREATE)
                }
            )
        )
    }

    fun onEditLessonMenuClick(lessonId: Long) {
        eventLiveData.dispatchEvent(
            TimetableEvent.NavigateToCreateEvent(
                Bundle().apply {
                    putLong(LessonCreate.LESSON_ID, lessonId)
                    putInt(LessonCreate.OPEN_TYPE, LessonCreate.EDIT)
                }
            )
        )
    }

    fun onCopyLessonMenuClick(lessonId: Long) {
        eventLiveData.dispatchEvent(
            TimetableEvent.NavigateToCreateEvent(
                Bundle().apply {
                    putLong(LessonCreate.LESSON_ID, lessonId)
                    putInt(LessonCreate.OPEN_TYPE, LessonCreate.COPY)
                }
            )
        )
    }

    fun onDeleteLessonMenuClick(lessonId: Long) {
        eventLiveData.dispatchEvent(TimetableEvent.ShowDeleteDialogEvent(lessonId))
    }

    fun onDeleteDialogOkButtonClick(lessonId: Long) {
        viewModelScope.launch {
            deleteLessonsUseCase(lessonId)
        }
    }
}
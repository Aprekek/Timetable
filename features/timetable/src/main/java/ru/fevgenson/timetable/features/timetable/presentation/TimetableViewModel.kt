package ru.fevgenson.timetable.features.timetable.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.fevgenson.libraries.navigation.di.NavigationConstants.LessonCreate
import ru.fevgenson.timetable.features.timetable.domain.usecase.GetLessonsUseCase
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventLiveData
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils
import ru.fevgenson.timetable.shared.lesson.domain.usecase.DeleteLessonsUseCase

@ExperimentalCoroutinesApi
class TimetableViewModel(
    private val deleteLessonsUseCase: DeleteLessonsUseCase,
    getLessonsUseCase: GetLessonsUseCase
) : ViewModel() {

    val eventLiveData = EventLiveData<TimetableEvent>()

    val selectedWeek = MutableStateFlow(DateUtils.getCurrentWeek())
    val selectedDay = MutableStateFlow(DateUtils.getCurrentDay())

    val dayViewModelsList = List(DateUtils.WEEK_DAYS) {
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
                    putInt(LessonCreate.WEEK_TYPE, selectedWeek.value)
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
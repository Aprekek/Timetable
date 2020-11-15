package ru.fevgenson.timetable.features.timetable.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import ru.fevgenson.timetable.features.timetable.domain.entities.TimetableLesson
import ru.fevgenson.timetable.features.timetable.domain.usecase.GetLessonsUseCase
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils

@ExperimentalCoroutinesApi
class PageDayViewModelDelegate(
    private val parentViewModel: TimetableViewModel,
    private val coroutineScope: CoroutineScope,
    currentDay: Int,
    getLessonsUseCase: GetLessonsUseCase
) {

    private val firstWeekLessons = getLessonsUseCase(
        weekType = DateUtils.FIRST_WEEK,
        day = currentDay
    ).stateIn(coroutineScope, SharingStarted.Lazily, null)

    private val secondWeekLessons = getLessonsUseCase(
        weekType = DateUtils.SECOND_WEEK,
        day = currentDay
    ).stateIn(coroutineScope, SharingStarted.Lazily, null)

    val lessons = MutableStateFlow<List<TimetableLesson>?>(null).apply {
        firstWeekLessons
            .filter { it != null }
            .filter { parentViewModel.selectedWeek.value == DateUtils.FIRST_WEEK }
            .onEach { value = it }
            .launchIn(coroutineScope)

        secondWeekLessons
            .filter { it != null }
            .filter { parentViewModel.selectedWeek.value == DateUtils.SECOND_WEEK }
            .onEach { value = it }
            .launchIn(coroutineScope)

        parentViewModel.selectedWeek.onEach {
            value = when (it) {
                DateUtils.FIRST_WEEK -> firstWeekLessons.value
                DateUtils.SECOND_WEEK -> secondWeekLessons.value
                else -> throw IllegalArgumentException()
            }
        }.launchIn(coroutineScope)
    }

    val uiState = lessons.map {
        when {
            it == null -> PageDayUIState.Loading
            it.isEmpty() -> PageDayUIState.Empty
            else -> PageDayUIState.Content
        }
    }

    fun copyLesson(lessonId: Long) {
        parentViewModel.onCopyLessonMenuClick(lessonId)
    }

    fun deleteLesson(lessonId: Long) {
        parentViewModel.onDeleteLessonMenuClick(lessonId)
    }

    fun editLesson(lessonId: Long) {
        parentViewModel.onEditLessonMenuClick(lessonId)
    }
}
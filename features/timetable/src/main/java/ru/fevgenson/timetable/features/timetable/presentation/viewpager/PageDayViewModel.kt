package ru.fevgenson.timetable.features.timetable.presentation.viewpager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import ru.fevgenson.timetable.features.timetable.domain.entities.Lesson
import ru.fevgenson.timetable.features.timetable.domain.usecase.GetLessonsUseCase
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils

class PageDayViewModel(
    private val currentWeekType: LiveData<Int>,
    currentDay: Int,
    getLessonsUseCase: GetLessonsUseCase
) : ViewModel() {

    val firstWeekLessons = getLessonsUseCase(
        weekType = DateUtils.FIRST_WEEK,
        day = currentDay
    )
    val secondWeekLessons = getLessonsUseCase(
        weekType = DateUtils.SECOND_WEEK,
        day = currentDay
    )

    val uiStateLiveData: LiveData<PageDayUIState> = MediatorLiveData<PageDayUIState>().apply {
        value = PageDayUIState.Loading
        addSource(firstWeekLessons) {
            if (currentWeekType.value == DateUtils.FIRST_WEEK) {
                value = setUIState(it, PageDayUIState.FirstWeek)
            }
        }
        addSource(secondWeekLessons) {
            if (currentWeekType.value == DateUtils.SECOND_WEEK) {
                value = setUIState(it, PageDayUIState.SecondWeek)
            }
        }
        addSource(currentWeekType) {
            value = when (it) {
                DateUtils.FIRST_WEEK -> setUIState(
                    firstWeekLessons.value,
                    PageDayUIState.FirstWeek
                )
                DateUtils.SECOND_WEEK -> setUIState(
                    secondWeekLessons.value,
                    PageDayUIState.SecondWeek
                )
                else -> throw IllegalArgumentException("Wrong week type constant: $it")
            }
        }
    }

    private fun setUIState(
        lessons: List<Lesson>?,
        successState: PageDayUIState
    ): PageDayUIState = when {
        lessons == null -> PageDayUIState.Loading
        lessons.isEmpty() -> PageDayUIState.Empty
        else -> successState
    }
}
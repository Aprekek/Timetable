package ru.fevgenson.timetable.features.timetable.presentation.viewpager

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.delay
import ru.fevgenson.timetable.features.timetable.domain.entities.Lesson
import ru.fevgenson.timetable.features.timetable.domain.usecase.GetLessonsUseCase
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils

class PageDayViewModel(
    private val currentWeekType: LiveData<Int>,
    currentDay: Int,
    getLessonsUseCase: GetLessonsUseCase
) : ViewModel() {

    private companion object {
        const val INIT_TIME = 500L
    }

    private var needInitTime = mutableSetOf<Int>()

    fun bind() {
        when (currentWeekType.value) {
            DateUtils.FIRST_WEEK -> needInitTime.add(DateUtils.SECOND_WEEK)
            DateUtils.SECOND_WEEK -> needInitTime.add(DateUtils.FIRST_WEEK)
        }
    }

    val firstWeekLessons = liveData {
        delay(INIT_TIME)
        emitSource(
            getLessonsUseCase(
                weekType = DateUtils.FIRST_WEEK,
                day = currentDay
            )
        )
    }
    val secondWeekLessons = liveData {
        delay(INIT_TIME)
        emitSource(
            getLessonsUseCase(
                weekType = DateUtils.SECOND_WEEK,
                day = currentDay
            )
        )
    }

    val firstWeekUIState: LiveData<PageDayUIState> = MediatorLiveData<PageDayUIState>().apply {
        initUIStateLiveData(firstWeekLessons, DateUtils.FIRST_WEEK)
    }
    val secondWeekUIState: LiveData<PageDayUIState> = MediatorLiveData<PageDayUIState>().apply {
        initUIStateLiveData(secondWeekLessons, DateUtils.SECOND_WEEK)
    }

    private fun MediatorLiveData<PageDayUIState>.initUIStateLiveData(
        contentLiveData: LiveData<List<Lesson>>,
        contentWeekType: Int
    ) {
        addSource(contentLiveData) {
            syncWithWeekType(contentWeekType, contentLiveData)
        }
        addSource(currentWeekType) {
            syncWithWeekType(contentWeekType, contentLiveData)
        }
    }

    private fun MediatorLiveData<PageDayUIState>.syncWithWeekType(
        contentWeekType: Int,
        content: LiveData<List<Lesson>>
    ) {
        value = when (currentWeekType.value) {
            contentWeekType -> if (needInitTime.contains(contentWeekType)) {
                Handler(Looper.getMainLooper()).postDelayed({
                    syncWithWeekType(contentWeekType, content)
                }, INIT_TIME)
                needInitTime.remove(contentWeekType)
                PageDayUIState.Loading
            } else {
                getContentUIState(content.value)
            }
            else -> PageDayUIState.Hide
        }
    }

    private fun getContentUIState(content: List<Lesson>?): PageDayUIState = when {
        content == null -> PageDayUIState.Loading
        content.isEmpty() -> PageDayUIState.Empty
        else -> PageDayUIState.Content
    }
}
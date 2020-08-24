package ru.fevgenson.timetable.features.timetable.presentation

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.get
import ru.fevgenson.libraries.navigation.di.NavigationConstants
import ru.fevgenson.timetable.features.timetable.domain.usecase.DeleteLessonUseCase
import ru.fevgenson.timetable.features.timetable.presentation.viewpager.PageDayViewModel
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventsDispatcher
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils

class TimetableViewModel(
    private val deleteLessonUseCase: DeleteLessonUseCase
) : ViewModel() {

    interface EventListener {
        fun navigateToCreate(bundle: Bundle)
        fun showDeleteDialog(lessonId: Long)
    }

    val selectedWeekLiveData = MutableLiveData(DateUtils.getCurrentWeek())
    val selectedDayLiveData = MutableLiveData(DateUtils.getCurrentDay())

    val eventsDispatcher = EventsDispatcher<EventListener>()

    val dayViewModelsList = List(DateUtils.WEEK_DAYS) {
        get(PageDayViewModel::class.java) {
            parametersOf(it, this)
        }
    }

    fun onCreateLessonButtonClick() {
        eventsDispatcher.dispatchEvent {
            with(NavigationConstants.LessonCreate) {
                navigateToCreate(
                    Bundle().apply {
                        putInt(
                            DAY,
                            requireNotNull(selectedDayLiveData.value) { "day can't be null" }
                        )
                        putInt(
                            WEEK_TYPE,
                            requireNotNull(selectedWeekLiveData.value) { "week can't be null" }
                        )
                        putInt(OPEN_TYPE, CREATE)
                    }
                )
            }
        }
    }

    fun onEditLessonMenuClick(lessonId: Long) {
        eventsDispatcher.dispatchEvent {
            with(NavigationConstants.LessonCreate) {
                navigateToCreate(
                    Bundle().apply {
                        putLong(LESSON_ID, lessonId)
                        putInt(OPEN_TYPE, EDIT)
                    }
                )
            }
        }
    }

    fun onCopyLessonMenuClick(lessonId: Long) {
        eventsDispatcher.dispatchEvent {
            with(NavigationConstants.LessonCreate) {
                navigateToCreate(
                    Bundle().apply {
                        putLong(LESSON_ID, lessonId)
                        putInt(OPEN_TYPE, COPY)
                    }
                )
            }
        }
    }

    fun onDeleteLessonMenuClick(lessonId: Long) {
        eventsDispatcher.dispatchEvent { showDeleteDialog(lessonId) }
    }

    fun onDeleteDialogOkButtonClick(lessonId: Long) {
        viewModelScope.launch {
            deleteLessonUseCase(lessonId)
        }
    }
}
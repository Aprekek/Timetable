package ru.fevgenson.timetable.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.fevgenson.timetable.shared.lesson.domain.usecase.GetLessonsFlowByWeekTypeAndDayUseCase
import ru.fevgenson.timetable.shared.settings.domain.usecase.GetForegroundServiceEnabledUseCase
import ru.fevgenson.timetable.shared.settings.domain.usecase.GetSavedThemeUseCase
import ru.fevgenson.timetable.shared.settings.domain.usecase.GetTimeBaseNotificationsEnabledUseCase
import ru.fevgenson.timetable.shared.timeutils.domain.entity.WeekAndDayPair

class MainActivityViewModel(
    private val getForegroundServiceEnabledUseCase: GetForegroundServiceEnabledUseCase,
    private val getTimeBaseNotificationsEnabledUseCase: GetTimeBaseNotificationsEnabledUseCase,
    private val getSavedThemeUseCase: GetSavedThemeUseCase,
    private val getLessonsFlowByWeekTypeAndDayUseCase: GetLessonsFlowByWeekTypeAndDayUseCase,
    currentDay: Flow<WeekAndDayPair>,
) : ViewModel() {

    val foregroundServiceEnabled: Boolean
        get() = getForegroundServiceEnabledUseCase()

    val timeBaseNotificationsEnabled: Boolean
        get() = getTimeBaseNotificationsEnabledUseCase()

    val savedTheme: Int
        get() = getSavedThemeUseCase()

    val lessons = currentDay.map {
        getLessonsFlowByWeekTypeAndDayUseCase(it.weekType, it.day)
    }
}
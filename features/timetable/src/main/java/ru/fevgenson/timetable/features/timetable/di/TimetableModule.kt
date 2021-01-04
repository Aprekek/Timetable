package ru.fevgenson.timetable.features.timetable.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fevgenson.timetable.features.timetable.domain.usecase.GetLessonsUseCase
import ru.fevgenson.timetable.features.timetable.presentation.TimetableViewModel
import ru.fevgenson.timetable.features.timetable.ui.TimetableFragment
import ru.fevgenson.timetable.features.timetable.ui.recyclerview.LessonViewHolderPool
import ru.fevgenson.timetable.shared.timeutils.ui.broadcastreceivers.DateBroadcastReceiver

@ExperimentalCoroutinesApi
private val viewModelModule = module {
    viewModel {
        TimetableViewModel(
            getLessonsUseCase = get(),
            deleteLessonsUseCase = get(),
            getCurrentDayUseCase = get(),
            getCurrentWeekTypeUseCase = get(),
            getWeekDatesScenario = get(),
            currentDayAndWeek = get<DateBroadcastReceiver>().callbacksFlow
        )
    }
}

private val useCaseModule = module {
    factory { GetLessonsUseCase(get()) }
}

@ExperimentalCoroutinesApi
private val viewHolderModule = module {
    scope<TimetableFragment> {
        scoped { LessonViewHolderPool() }
    }
}

@ExperimentalCoroutinesApi
val timetableListModules = listOf(
    viewModelModule,
    useCaseModule,
    viewHolderModule
)
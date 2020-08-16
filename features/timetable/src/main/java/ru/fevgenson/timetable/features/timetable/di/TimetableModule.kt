package ru.fevgenson.timetable.features.timetable.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fevgenson.timetable.features.timetable.presentation.TimetableViewModel
import ru.fevgenson.timetable.features.timetable.presentation.viewpager.PageDayViewModel

private val viewModelModule = module {
    viewModel { TimetableViewModel() }
    viewModel { (currentDay: Int, timetableViewModel: TimetableViewModel) ->
        PageDayViewModel(
            currentDay = currentDay,
            currentWeekType = timetableViewModel.selectedWeekLiveData
        )
    }
}

val timetableListModules = listOf(
    viewModelModule
)
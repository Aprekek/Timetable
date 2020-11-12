package ru.fevgenson.timetable.features.timetable.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fevgenson.timetable.features.timetable.presentation.TimetableFragment
import ru.fevgenson.timetable.features.timetable.presentation.TimetableViewModel
import ru.fevgenson.timetable.features.timetable.presentation.recyclerview.LessonViewHolderPool
import ru.fevgenson.timetable.features.timetable.presentation.viewpager.PageDayViewModel

private val viewModelModule = module {
    viewModel {
        TimetableViewModel(get())
    }
    factory { (currentDay: Int, timetableViewModel: TimetableViewModel) ->
        PageDayViewModel(
            currentDay = currentDay,
            parentViewModel = timetableViewModel,
            getLessonsByDayUseCase = get()
        )
    }
}

private val viewHolderModule = module {
    scope<TimetableFragment> {
        scoped { LessonViewHolderPool() }
    }
}

val timetableListModules = listOf(
    viewModelModule,
    viewHolderModule
)
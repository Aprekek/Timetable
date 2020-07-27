package ru.fevgenson.timetable.features.timetable.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fevgenson.timetable.features.timetable.presentation.TimetableViewModel

private val viewModelModule = module {
    viewModel { TimetableViewModel() }
}

val timetableListModules = listOf(
    viewModelModule
)
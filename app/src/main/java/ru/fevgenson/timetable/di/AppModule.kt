package ru.fevgenson.timetable.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fevgenson.timetable.presentation.MainActivityViewModel

val appModule = module {
    viewModel {
        MainActivityViewModel(
            getForegroundServiceEnabledUseCase = get(),
            getSavedThemeUseCase = get()
        )
    }
}
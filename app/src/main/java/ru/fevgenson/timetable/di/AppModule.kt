package ru.fevgenson.timetable.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fevgenson.timetable.presentation.MainActivityViewModel
import ru.fevgenson.timetable.shared.timeutils.ui.broadcastreceivers.DateBroadcastReceiver

val appModule = module {
    viewModel {
        MainActivityViewModel(
            getForegroundServiceEnabledUseCase = get(),
            getSavedThemeUseCase = get(),
            getTimeBaseNotificationsEnabledUseCase = get(),
            getLessonsFlowByWeekTypeAndDayUseCase = get(),
            currentDay = get<DateBroadcastReceiver>().callbacksFlow
        )
    }
}
package ru.fevgenson.timetable.shared.timeutils.ui.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.fevgenson.timetable.shared.timeutils.ui.broadcastreceivers.DateBroadcastReceiver
import ru.fevgenson.timetable.shared.timeutils.ui.broadcastreceivers.MinutesBroadcastReceiver
import ru.fevgenson.timetable.shared.timeutils.ui.formatter.TimePositionFormatter
import ru.fevgenson.timetable.shared.timeutils.ui.formatter.TimePositionFormatterImpl

val timeUtilsUiModule = module {
    single {
        DateBroadcastReceiver(
            getCurrentWeekTypeUseCase = get(),
            getCurrentDayUseCase = get()
        )
    }
    single { MinutesBroadcastReceiver(get()) }
    single<TimePositionFormatter> {
        TimePositionFormatterImpl(
            getCurrentTimeUseCase = get(),
            timeFormatter = get(),
            context = androidContext()
        )
    }
}
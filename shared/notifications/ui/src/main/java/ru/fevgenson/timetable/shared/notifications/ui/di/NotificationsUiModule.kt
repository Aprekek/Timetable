package ru.fevgenson.timetable.shared.notifications.ui.di

import androidx.work.WorkManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.fevgenson.timetable.shared.notifications.ui.builders.ChannelBuilder
import ru.fevgenson.timetable.shared.notifications.ui.builders.NotificationBuilder
import ru.fevgenson.timetable.shared.notifications.ui.channels.Channels
import ru.fevgenson.timetable.shared.notifications.ui.schedulers.TimeBaseNotificationScheduler

val notificationsUiModule = module {
    factory { ChannelBuilder(get()) }
    factory { (channel: Channels) ->
        NotificationBuilder(
            context = get(),
            timePositionFormatter = get(),
            channel = channel
        )
    }
    single {
        TimeBaseNotificationScheduler(
            getCurrentDayUseCase = get(),
            getMinutesBeforeStartScenario = get(),
            getCurrentWeekTypeUseCase = get(),
            getNextDayScenario = get(),
            getNextDayWeekTypeScenario = get(),
            getTimeBeforeNextDayUseCase = get(),
            getLessonsByWeekTypeAndDayUseCase = get(),
            workManager = WorkManager.getInstance(androidContext())
        )
    }
}
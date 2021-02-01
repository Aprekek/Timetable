package ru.fevgenson.timetable.shared.notifications.ui.di

import org.koin.dsl.module
import ru.fevgenson.timetable.shared.notifications.ui.builders.ChannelBuilder
import ru.fevgenson.timetable.shared.notifications.ui.builders.NotificationBuilder
import ru.fevgenson.timetable.shared.notifications.ui.channels.Channels

val notificationsUiModule = module {
    factory { ChannelBuilder(get()) }
    factory { (channel: Channels) ->
        NotificationBuilder(
            context = get(),
            timePositionFormatter = get(),
            channel = channel
        )
    }
}
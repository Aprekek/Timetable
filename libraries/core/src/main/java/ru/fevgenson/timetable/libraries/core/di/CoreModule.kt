package ru.fevgenson.timetable.libraries.core.di

import org.koin.dsl.module
import ru.fevgenson.timetable.libraries.core.utils.broadcastrecivers.DateBroadcastReceiver
import ru.fevgenson.timetable.libraries.core.utils.broadcastrecivers.MinutesBroadcastReceiver

private val broadcastReceiverModule = module {
    single { DateBroadcastReceiver() }
    single { MinutesBroadcastReceiver() }
}

val coreModule = listOf(
    broadcastReceiverModule
)
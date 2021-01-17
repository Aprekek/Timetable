package ru.fevgenson.timetable.shared.notifications.domain.di

import org.koin.dsl.module
import ru.fevgenson.timetable.shared.notifications.domain.scenario.GetNotEndNotificationLessonsScenario
import ru.fevgenson.timetable.shared.notifications.domain.usecase.GetNotificationLessonsFlowUseCase

val notificationsDomainModule = module {
    factory {
        GetNotificationLessonsFlowUseCase(get())
        GetNotEndNotificationLessonsScenario(
            getCurrentTimeUseCase = get(),
            timeFormatter = get()
        )
    }
}
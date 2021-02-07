package ru.fevgenson.timetable.shared.notifications.domain.di

import org.koin.dsl.module
import ru.fevgenson.timetable.shared.notifications.domain.scenario.GetNotEndNotificationLessonsScenario
import ru.fevgenson.timetable.shared.notifications.domain.usecase.GetNotificationLessonUseCase
import ru.fevgenson.timetable.shared.notifications.domain.usecase.GetNotificationLessonsFlowUseCase

val notificationsDomainModule = module {
    factory {
        GetNotificationLessonsFlowUseCase(get())
    }
    factory {
        GetNotificationLessonUseCase(get())
    }
    factory {
        GetNotEndNotificationLessonsScenario(
            getCurrentTimeUseCase = get(),
            timeFormatter = get()
        )
    }
}
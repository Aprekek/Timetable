package ru.fevgenson.timetable.features.notifications.di

import org.koin.dsl.module
import ru.fevgenson.timetable.features.notifications.domain.usecase.GetLessonsUseCase

private val useCaseModule = module {
    factory { GetLessonsUseCase(get()) }
}

val notificationsModule = listOf(
    useCaseModule
)
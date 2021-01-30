package ru.fevgenson.timetable.shared.settings.domain.di

import org.koin.dsl.module
import ru.fevgenson.timetable.shared.settings.domain.usecase.GetForegroundServiceEnabledUseCase
import ru.fevgenson.timetable.shared.settings.domain.usecase.GetSavedThemeUseCase
import ru.fevgenson.timetable.shared.settings.domain.usecase.SaveForegroundServiceEnabledUseCase
import ru.fevgenson.timetable.shared.settings.domain.usecase.SaveThemeUseCase

val settingsDomainModule = module {
    factory { GetForegroundServiceEnabledUseCase(get()) }
    factory { GetSavedThemeUseCase(get()) }
    factory { SaveForegroundServiceEnabledUseCase(get()) }
    factory { SaveThemeUseCase(get()) }
}
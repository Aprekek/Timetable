package ru.fevgenson.timetable.shared.settings.domain.di

import org.koin.dsl.module
import ru.fevgenson.timetable.shared.settings.domain.usecase.*

val settingsDomainModule = module {
    factory { GetForegroundServiceEnabledUseCase(get()) }
    factory { GetSavedThemeUseCase(get()) }
    factory { GetTimeBaseNotificationsEnabledUseCase(get()) }
    factory { SaveForegroundServiceEnabledUseCase(get()) }
    factory { SaveThemeUseCase(get()) }
    factory { SaveTimeBaseNotificationsEnabledUseCase(get()) }
}
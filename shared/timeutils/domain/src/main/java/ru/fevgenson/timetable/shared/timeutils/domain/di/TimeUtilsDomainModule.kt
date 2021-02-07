package ru.fevgenson.timetable.shared.timeutils.domain.di

import org.koin.dsl.module
import ru.fevgenson.timetable.shared.timeutils.domain.converter.CalendarConverter
import ru.fevgenson.timetable.shared.timeutils.domain.converter.CalendarConverterImpl
import ru.fevgenson.timetable.shared.timeutils.domain.formatter.TimeFormatter
import ru.fevgenson.timetable.shared.timeutils.domain.formatter.TimeFormatterImpl
import ru.fevgenson.timetable.shared.timeutils.domain.scenario.*
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentDayUseCase
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentTimeUseCase
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentWeekTypeUseCase
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetTimeBeforeNextDayUseCase

val timeUtilsDomainModule = module {
    single<TimeFormatter> { TimeFormatterImpl() }
    single<CalendarConverter> { CalendarConverterImpl() }
    single { GetCurrentTimeUseCase() }
    single { GetCurrentDayUseCase(get()) }
    single { GetCurrentWeekTypeUseCase(get()) }
    single { GetTimeBeforeNextDayUseCase() }
    single {
        CurrentTimeInThisDiapasonScenario(
            getCurrentTimeUseCase = get(),
            timeFormatter = get()
        )
    }
    single {
        GetMinutesBeforeStartScenario(
            getCurrentTimeUseCase = get(),
            timeFormatter = get()
        )
    }
    single { GetWeekDatesScenario(get()) }
    single { GetNextDayScenario(get()) }
    single {
        GetNextDayWeekTypeScenario(
            getCurrentWeekTypeUseCase = get(),
            getCurrentDayUseCase = get()
        )
    }
}
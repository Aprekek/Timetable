package ru.fevgenson.timetable.shared.timeutils.domain.di

import org.koin.dsl.module
import ru.fevgenson.timetable.shared.timeutils.domain.converter.CalendarConverter
import ru.fevgenson.timetable.shared.timeutils.domain.converter.CalendarConverterImpl
import ru.fevgenson.timetable.shared.timeutils.domain.formatter.TimeFormatter
import ru.fevgenson.timetable.shared.timeutils.domain.formatter.TimeFormatterImpl
import ru.fevgenson.timetable.shared.timeutils.domain.scenario.CurrentTimeInThisDiapasonScenario
import ru.fevgenson.timetable.shared.timeutils.domain.scenario.GetWeekDatesScenario
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentDayUseCase
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentTimeUseCase
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentWeekTypeUseCase

val timeUtilsDomainModule = module {
    single<TimeFormatter> { TimeFormatterImpl() }
    single<CalendarConverter> { CalendarConverterImpl() }
    single { GetCurrentTimeUseCase() }
    single { GetCurrentDayUseCase(get()) }
    single { GetCurrentWeekTypeUseCase(get()) }
    single {
        CurrentTimeInThisDiapasonScenario(
            getCurrentTimeUseCase = get(),
            timeFormatter = get()
        )
    }
    single { GetWeekDatesScenario(get()) }
}
package ru.fevgenson.timetable.shared.lesson.di

import org.koin.dsl.module
import ru.fevgenson.timetable.shared.lesson.domain.usecase.*

private val useCaseModule = module {
    factory { GetClassroomsValuesUseCase(get()) }
    factory { GetClassroomsValuesUseCaseFlow(get()) }
    factory { GetHousingsValuesUseCase(get()) }
    factory { GetHousingsValuesUseCaseFlow(get()) }
    factory { GetSubjectsValuesUseCase(get()) }
    factory { GetSubjectsValuesUseCaseFlow(get()) }
    factory { GetTeachersUseCase(get()) }
    factory { GetTeachersUseCaseFlow(get()) }
    factory { GetTimesValuesUseCase(get()) }
    factory { GetTimesValuesUseCaseFlow(get()) }
    factory { GetTypesValuesUseCase(get()) }
    factory { GetLessonsByDayUseCase(get()) }
    factory { GetLessonsBySubcategoryUseCase(get()) }
    factory { SaveLessonsUseCase(get()) }
    factory { GetLessonUseCase(get()) }
    factory { UpdateLessonUseCase(get()) }
    factory { DeleteLessonUseCase(get()) }
}

val sharedModule = useCaseModule
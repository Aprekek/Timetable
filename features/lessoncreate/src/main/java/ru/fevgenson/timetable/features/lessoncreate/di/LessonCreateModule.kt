package ru.fevgenson.timetable.features.lessoncreate.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fevgenson.timetable.features.lessoncreate.domain.usecase.*
import ru.fevgenson.timetable.features.lessoncreate.presentation.LessonCreateViewModel

private val viewModelModule = module {
    viewModel { (weekType: Int, day: Int, id: Long, openTypeInt: Int) ->
        LessonCreateViewModel(
            weekType = weekType,
            day = day,
            id = id,
            openType = openTypeInt,
            getClassroomsValuesUseCase = get(),
            getHousingsValuesUseCase = get(),
            getSubjectsValuesUseCase = get(),
            getTimesValuesUseCase = get(),
            getTypesValuesUseCase = get(),
            getTeachersUseCase = get(),
            getLessonUseCase = get(),
            saveLessonsUseCase = get(),
            updateLessonUseCase = get()
        )
    }
}

private val useCaseModule = module {
    factory { GetClassroomsValuesUseCase(get()) }
    factory { GetHousingsValuesUseCase(get()) }
    factory { GetLessonUseCase(get()) }
    factory { GetSubjectsValuesUseCase(get()) }
    factory { GetTeachersUseCase(get()) }
    factory { GetTimesValuesUseCase(get()) }
    factory { GetTypesValuesUseCase(get()) }
    factory { SaveLessonsUseCase(get()) }
    factory { UpdateLessonUseCase(get()) }
}

val lessonCreateListModules = listOf(
    viewModelModule,
    useCaseModule
)
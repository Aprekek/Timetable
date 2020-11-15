package ru.fevgenson.timetable.features.timetable.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fevgenson.timetable.features.timetable.domain.usecase.DeleteLessonUseCase
import ru.fevgenson.timetable.features.timetable.domain.usecase.GetLessonsUseCase
import ru.fevgenson.timetable.features.timetable.presentation.TimetableViewModel
import ru.fevgenson.timetable.features.timetable.ui.TimetableFragment
import ru.fevgenson.timetable.features.timetable.ui.recyclerview.LessonViewHolderPool

@ExperimentalCoroutinesApi
private val viewModelModule = module {
    viewModel {
        TimetableViewModel(
            getLessonsUseCase = get(),
            deleteLessonUseCase = get()
        )
    }
}

private val useCaseModule = module {
    factory { GetLessonsUseCase(get()) }
    factory { DeleteLessonUseCase(get()) }
}

@ExperimentalCoroutinesApi
private val viewHolderModule = module {
    scope<TimetableFragment> {
        scoped { LessonViewHolderPool() }
    }
}

@ExperimentalCoroutinesApi
val timetableListModules = listOf(
    viewModelModule,
    useCaseModule,
    viewHolderModule
)
package ru.fevgenson.timetable.features.lessoncreate.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fevgenson.timetable.features.lessoncreate.presentation.LessonCreateViewModel
import ru.fevgenson.timetable.libraries.core.providers.ResourceProvider

private val viewModelModule = module {
    viewModel { (resourceProvider: ResourceProvider) ->
        LessonCreateViewModel(resourceProvider)
    }
}

val lessonCreateListModules = listOf(viewModelModule)
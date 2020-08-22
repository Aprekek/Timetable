package ru.fevgenson.timetable.features.lessoncreate.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fevgenson.timetable.features.lessoncreate.presentation.LessonCreateViewModel

private val viewModelModule = module {
    viewModel { (weekType: Int, day: Int) ->
        LessonCreateViewModel(weekType, day)
    }
}

val lessonCreateListModules = listOf(viewModelModule)
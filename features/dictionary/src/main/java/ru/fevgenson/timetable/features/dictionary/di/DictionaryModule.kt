package ru.fevgenson.timetable.features.dictionary.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fevgenson.timetable.features.dictionary.presentation.dictionary.DictionaryViewModel
import ru.fevgenson.timetable.features.dictionary.presentation.dictionary.viewpager.PageCategoryViewModel
import ru.fevgenson.timetable.features.dictionary.presentation.lessonsbycategory.ListOfLessonsByCategoryViewModel

private val viewModelModule = module {
    viewModel {
        DictionaryViewModel()
    }
    viewModel { (categoryItem: String, categoryName: String, categoryType: Int) ->
        ListOfLessonsByCategoryViewModel(categoryItem, categoryName, categoryType)
    }

    factory { (categoryType: Int, parentViewModel: DictionaryViewModel) ->
        PageCategoryViewModel(
            categoryType = categoryType,
            parentViewModel = parentViewModel,
            getSubjectsValuesUseCaseFlow = get(),
            getClassroomsValuesUseCaseFlow = get(),
            getHousingsValuesUseCaseFlow = get(),
            getTeachersUseCaseFlow = get(),
            getTimesValuesUseCaseFlow = get()
        )
    }
}

val dictionaryListModules = listOf(viewModelModule)
package ru.fevgenson.timetable.features.dictionary.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fevgenson.timetable.features.dictionary.domain.Categories
import ru.fevgenson.timetable.features.dictionary.domain.scenario.GetListOfSubcategoriesScenario
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

    factory { (categoryType: Categories.CategoryTypes, parentViewModel: DictionaryViewModel) ->
        PageCategoryViewModel(
            getListOfSubcategoriesScenario = get(),
            categoryType,
            parentViewModel
        )
    }

    factory {
        GetListOfSubcategoriesScenario(
            getAllSubjectsFlowUseCase = get(),
            getAllTeachersFlowUseCase = get(),
            getAllHousingsFlowUseCase = get(),
            getAllClassroomsFlowUseCase = get(),
            getAllTimesFlowUseCase = get()
        )
    }
}

val dictionaryListModules = listOf(viewModelModule)
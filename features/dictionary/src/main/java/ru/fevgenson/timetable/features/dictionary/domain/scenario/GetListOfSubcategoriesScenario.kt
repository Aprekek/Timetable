package ru.fevgenson.timetable.features.dictionary.domain.scenario

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.fevgenson.timetable.features.dictionary.domain.Categories
import ru.fevgenson.timetable.shared.lesson.domain.entity.SubcategoryEntity
import ru.fevgenson.timetable.shared.lesson.domain.usecase.*

class GetListOfSubcategoriesScenario(
    private val getAllSubjectsFlowUseCase: GetAllSubjectsFlowUseCase,
    private val getAllTeachersFlowUseCase: GetAllTeachersFlowUseCase,
    private val getAllHousingsFlowUseCase: GetAllHousingsFlowUseCase,
    private val getAllClassroomsFlowUseCase: GetAllClassroomsFlowUseCase,
    private val getAllTimesFlowUseCase: GetAllTimesFlowUseCase,
    private val getAllTypesFlowUseCase: GetAllTypesFlowUseCase
) {

    operator fun invoke(category: Categories.CategoryTypes): Flow<List<SubcategoryEntity>> =
        when (category) {
            Categories.CategoryTypes.SUBJECT_CATEGORY -> getAllSubjectsFlowUseCase()
            Categories.CategoryTypes.TEACHER_CATEGORY -> getAllTeachersFlowUseCase()
            Categories.CategoryTypes.HOUSING_CATEGORY -> getAllHousingsFlowUseCase()
            Categories.CategoryTypes.CLASSROOM_CATEGORY -> getAllClassroomsFlowUseCase()
            Categories.CategoryTypes.TIME_CATEGORY -> getAllTimesFlowUseCase()
            Categories.CategoryTypes.TYPE_CATEGORY -> getAllTypesFlowUseCase()
        }.map { list -> list.map { it.toSubcategoryEntity() } }
}
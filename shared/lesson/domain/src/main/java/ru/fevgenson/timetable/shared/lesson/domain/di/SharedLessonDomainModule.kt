package ru.fevgenson.timetable.shared.lesson.domain.di

import org.koin.dsl.module
import ru.fevgenson.timetable.shared.lesson.domain.usecase.*

val sharedLessonDomainModule = module {

    factory { GetClassroomUseCase(get()) }
    factory { GetAllClassroomsUseCase(get()) }
    factory { GetAllClassroomsFlowUseCase(get()) }
    factory { SaveClassroomsUseCase(get()) }
    factory { DeleteClassroomsUseCase(get()) }
    factory { DeleteAllClassroomsUseCase(get()) }

    factory { GetHousingUseCase(get()) }
    factory { GetAllHousingsUseCase(get()) }
    factory { GetAllHousingsFlowUseCase(get()) }
    factory { SaveHousingsUseCase(get()) }
    factory { DeleteHousingsUseCase(get()) }
    factory { DeleteAllHousingsUseCase(get()) }

    factory { GetSubjectUseCase(get()) }
    factory { GetAllSubjectsUseCase(get()) }
    factory { GetAllSubjectsFlowUseCase(get()) }
    factory { SaveSubjectsUseCase(get()) }
    factory { DeleteSubjectsUseCase(get()) }
    factory { DeleteAllSubjectsUseCase(get()) }

    factory { GetTeacherUseCase(get()) }
    factory { GetAllTeachersUseCase(get()) }
    factory { GetAllTeachersFlowUseCase(get()) }
    factory { SaveTeachersUseCase(get()) }
    factory { DeleteTeachersUseCase(get()) }
    factory { DeleteAllTeachersUseCase(get()) }

    factory { GetTimeUseCase(get()) }
    factory { GetAllTimesUseCase(get()) }
    factory { GetAllTimesFlowUseCase(get()) }
    factory { SaveTimesUseCase(get()) }
    factory { DeleteTimesUseCase(get()) }
    factory { DeleteAllTimesUseCase(get()) }

    factory { GetTypeUseCase(get()) }
    factory { GetAllTypesUseCase(get()) }
    factory { GetAllTypesFlowUseCase(get()) }
    factory { SaveTypesUseCase(get()) }
    factory { DeleteTypesUseCase(get()) }
    factory { DeleteAllTypesUseCase(get()) }

    factory { GetLessonByIdUseCase(get()) }
    factory { GetLessonsByWeekTypeAndDayUseCase(get()) }
    factory { GetLessonsFlowByWeekTypeAndDayUseCase(get()) }
    factory { GetAllLessonsUseCase(get()) }
    factory { GetAllLessonsFlowUseCase(get()) }
    factory { SaveLessonsUseCase(get()) }
    factory { DeleteLessonsUseCase(get()) }
}
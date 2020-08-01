package ru.fevgenson.timetable.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.fevgenson.libraries.navigation.di.NavigationDIConstants
import ru.fevgenson.timetable.R

private val globalHostModule = module {
    single(named(NavigationDIConstants.GLOBAL_HOST)) { R.id.global_host }
}

private val navigationModule = module {
    single(named(NavigationDIConstants.MAIN_TO_LESSON_CREATE)) {
        R.id.action_mainFragment_to_lessonCreateFragment
    }
}

val appListModules = listOf(
    globalHostModule,
    navigationModule
)
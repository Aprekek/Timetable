package ru.fevgenson.timetable.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.fevgenson.libraries.navigation.di.NavigationDIConstants
import ru.fevgenson.timetable.R

private val globalHostModule = module {
    single(named(NavigationDIConstants.GLOBAL_HOST)) { R.id.global_host }
}

val appListModules = listOf(globalHostModule)
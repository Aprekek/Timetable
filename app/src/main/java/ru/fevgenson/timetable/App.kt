package ru.fevgenson.timetable

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.fevgenson.timetable.features.dictionary.di.dictionaryListModules
import ru.fevgenson.timetable.features.lessoncreate.di.lessonCreateListModules
import ru.fevgenson.timetable.features.timetable.di.timetableListModules

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()

            modules(lessonCreateListModules)
            modules(timetableListModules)
            modules(dictionaryListModules)
        }
    }
}
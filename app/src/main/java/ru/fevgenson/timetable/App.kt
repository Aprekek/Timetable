package ru.fevgenson.timetable

import android.app.Application
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.fevgenson.timetable.features.dictionary.di.dictionaryListModules
import ru.fevgenson.timetable.features.lessoncreate.di.lessonCreateListModules
import ru.fevgenson.timetable.features.notifications.di.notificationsModule
import ru.fevgenson.timetable.features.settings.di.settingsModule
import ru.fevgenson.timetable.features.timetable.di.timetableListModules
import ru.fevgenson.timetable.libraries.core.di.coreModule
import ru.fevgenson.timetable.libraries.database.di.databaseModule

class App : Application() {

    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            androidFileProperties()

            modules(lessonCreateListModules)
            modules(timetableListModules)
            modules(dictionaryListModules)
            modules(databaseModule)
            modules(coreModule)
            modules(notificationsModule)
            modules(settingsModule)
        }
    }
}
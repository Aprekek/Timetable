package ru.fevgenson.timetable

import android.app.Application
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.fevgenson.timetable.di.appModule
import ru.fevgenson.timetable.features.dictionary.di.dictionaryListModules
import ru.fevgenson.timetable.features.lessoncreate.di.lessonCreateListModules
import ru.fevgenson.timetable.features.settings.di.settingsModule
import ru.fevgenson.timetable.features.timetable.di.timetableListModules
import ru.fevgenson.timetable.libraries.core.di.coreModule
import ru.fevgenson.timetable.libraries.database.di.databaseModule
import ru.fevgenson.timetable.shared.lesson.data.di.sharedLessonDataModule
import ru.fevgenson.timetable.shared.lesson.domain.di.sharedLessonDomainModule
import ru.fevgenson.timetable.shared.notifications.domain.di.notificationsDomainModule
import ru.fevgenson.timetable.shared.notifications.ui.di.notificationsUiModule
import ru.fevgenson.timetable.shared.settings.data.di.settingsDataModule
import ru.fevgenson.timetable.shared.settings.domain.di.settingsDomainModule
import ru.fevgenson.timetable.shared.timeutils.domain.di.timeUtilsDomainModule
import ru.fevgenson.timetable.shared.timeutils.ui.di.timeUtilsUiModule

class App : Application() {

    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            androidFileProperties()

            modules(appModule)
            modules(lessonCreateListModules)
            modules(timetableListModules)
            modules(dictionaryListModules)
            modules(databaseModule)
            modules(coreModule)
            modules(settingsModule)
            modules(sharedLessonDomainModule)
            modules(sharedLessonDataModule)
            modules(timeUtilsDomainModule)
            modules(timeUtilsUiModule)
            modules(settingsDomainModule)
            modules(settingsDataModule)
            modules(notificationsDomainModule)
            modules(notificationsUiModule)
        }
    }
}
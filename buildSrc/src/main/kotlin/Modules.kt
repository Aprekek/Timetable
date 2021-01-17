object Modules {

    object Features {

        const val LESSON_CREATE = ":features:lessoncreate"
        const val MAIN = ":features:main"
        const val TIMETABLE = ":features:timetable"
        const val HOMEWORK = ":features:homework"
        const val DICTIONARY = ":features:dictionary"
        const val SETTINGS = ":features:settings"
        const val NOTIFICATIONS = ":features:notifications"
    }

    object Libraries {

        const val NAVIGATION = ":libraries:navigation"
        const val THEMES = ":libraries:themes"
        const val CORE = ":libraries:core"
        const val DATABASE = ":libraries:database"
        const val FLOW_BINDING = ":libraries:flowbinding"
    }

    object Shared {

        object Lesson {

            const val DOMAIN = ":shared:lesson:domain"
            const val DATA = ":shared:lesson:data"
        }

        object TimeUtils {

            const val DOMAIN = ":shared:timeutils:domain"
            const val UI = ":shared:timeutils:ui"
        }

        object Settings {

            const val DOMAIN = ":shared:settings:domain"
            const val DATA = ":shared:settings:data"
        }

        object Notifications {

            const val DOMAIN = ":shared:notifications:domain"
            const val UI = ":shared:notifications:ui"
        }
    }
}
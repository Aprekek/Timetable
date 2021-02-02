@file:Suppress("UnstableApiUsage")

plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.GOOGLE_SERVICES)
}

android.buildFeatures.dataBinding = true

dependencies {
    implementation(Dependencies.Kotlin.KOTLIN_STANDARD_LIBRARY)
    implementation(Dependencies.Kotlin.KOTLIN_REFLECT)
    implementation(Dependencies.CORE)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.MATERIAL)

    implementation(Dependencies.Google.FIREBASE_ANALYTICS)

    kapt(Dependencies.DATABINDING)

    implementation(Dependencies.Koin.KOIN_CORE)
    implementation(Dependencies.Koin.KOIN_EXT)
    implementation(Dependencies.Koin.KOIN_SCOPE)

    implementation(Dependencies.Navigation.NAVIGATION_FRAGMENT_KTX)
    implementation(Dependencies.Navigation.NAVIGATION_UI_KTX)

    implementation(project(Modules.Features.MAIN))
    implementation(project(Modules.Features.TIMETABLE))
    implementation(project(Modules.Features.HOMEWORK))
    implementation(project(Modules.Features.DICTIONARY))
    implementation(project(Modules.Features.SETTINGS))
    implementation(project(Modules.Features.LESSON_CREATE))

    implementation(project(Modules.Libraries.NAVIGATION))
    implementation(project(Modules.Libraries.THEMES))
    implementation(project(Modules.Libraries.CORE))
    implementation(project(Modules.Libraries.DATABASE))
    implementation(project(Modules.Libraries.FLOW_BINDING))

    implementation(project(Modules.Shared.Lesson.DOMAIN))
    implementation(project(Modules.Shared.Lesson.DATA))

    implementation(project(Modules.Shared.TimeUtils.DOMAIN))
    implementation(project(Modules.Shared.TimeUtils.UI))

    implementation(project(Modules.Shared.Settings.DOMAIN))
    implementation(project(Modules.Shared.Settings.DATA))

    implementation(project(Modules.Shared.Notifications.DOMAIN))
    implementation(project(Modules.Shared.Notifications.UI))

    implementation(project(Modules.Shared.Backup.DOMAIN))
}
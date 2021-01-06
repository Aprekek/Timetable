plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
}

dependencies {
    implementation(Dependencies.Kotlin.KOTLIN_STANDARD_LIBRARY)
    implementation(Dependencies.Kotlin.KOTLIN_REFLECT)
    implementation(Dependencies.CORE)
    implementation(Dependencies.APP_COMPAT)

    implementation(Dependencies.Koin.KOIN_CORE)
    implementation(Dependencies.Koin.KOIN_EXT)
    implementation(Dependencies.Koin.KOIN_SCOPE)

    implementation(Dependencies.Navigation.NAVIGATION_FRAGMENT_KTX)
    implementation(Dependencies.Navigation.NAVIGATION_UI_KTX)

    implementation(project(Modules.Libraries.NAVIGATION))
    implementation(project(Modules.Libraries.THEMES))
    implementation(project(Modules.Libraries.CORE))

    implementation(project(Modules.Features.TIMETABLE))
    implementation(project(Modules.Features.HOMEWORK))
    implementation(project(Modules.Features.DICTIONARY))
    implementation(project(Modules.Features.SETTINGS))
}
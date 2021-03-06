plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
}

dependencies {
    implementation(Dependencies.Kotlin.KOTLIN_STANDARD_LIBRARY)
    implementation(Dependencies.Kotlin.KOTLIN_REFLECT)
    implementation(Dependencies.CORE)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.CONSTRAINT_LAYOUT)

    implementation(Dependencies.Koin.KOIN_CORE)
    implementation(Dependencies.Koin.KOIN_EXT)
    implementation(Dependencies.Koin.KOIN_SCOPE)

    implementation(Dependencies.Navigation.NAVIGATION_FRAGMENT_KTX)
    implementation(Dependencies.Navigation.NAVIGATION_UI_KTX)

    implementation(project(Modules.Libraries.NAVIGATION))
    implementation(project(Modules.Libraries.THEMES))
    implementation(project(Modules.Libraries.CORE))
    implementation(project(Modules.Libraries.FLOW_BINDING))

    implementation(project(Modules.Shared.Lesson.DOMAIN))

    implementation(project(Modules.Shared.TimeUtils.DOMAIN))
    implementation(project(Modules.Shared.TimeUtils.UI))
}
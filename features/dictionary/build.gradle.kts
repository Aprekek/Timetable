@file:Suppress("UnstableApiUsage")

plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
}

android.buildFeatures.dataBinding = true

dependencies {
    implementation(Dependencies.Kotlin.KOTLIN_STANDARD_LIBRARY)
    implementation(Dependencies.Kotlin.KOTLIN_REFLECT)
    implementation(Dependencies.CORE)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.VIEW_PAGER_2)

    kapt(Dependencies.DATABINDING)

    implementation(Dependencies.CONSTRAINT_LAYOUT)

    implementation(Dependencies.Lifecycle.LIFECYCLE_LIVEDATA)

    implementation(Dependencies.Koin.KOIN_CORE)
    implementation(Dependencies.Koin.KOIN_EXT)
    implementation(Dependencies.Koin.KOIN_SCOPE)

    implementation(Dependencies.Navigation.NAVIGATION_FRAGMENT_KTX)
    implementation(Dependencies.Navigation.NAVIGATION_UI_KTX)

    implementation(project(Modules.Libraries.DATABASE))
    implementation(project(Modules.Libraries.NAVIGATION))
    implementation(project(Modules.Libraries.THEMES))
    implementation(project(Modules.Libraries.CORE))
}
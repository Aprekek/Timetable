plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
}

dependencies {
    implementation(Dependencies.Kotlin.KOTLIN_STANDARD_LIBRARY)
    implementation(Dependencies.CORE)

    implementation(Dependencies.Koin.KOIN_CORE)

    implementation(project(Modules.Shared.Settings.DOMAIN))
}
plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
}

dependencies {
    implementation(Dependencies.Kotlin.KOTLIN_STANDARD_LIBRARY)
    implementation(Dependencies.Kotlin.KOTLIN_REFLECT)
    implementation(Dependencies.CORE)

    implementation(Dependencies.Lifecycle.LIFECYCLE_LIVEDATA)
    implementation(Dependencies.Lifecycle.LIFECYCLE_EXTENSIONS)

    implementation(Dependencies.Koin.KOIN_CORE)
    implementation(Dependencies.Koin.KOIN_EXT)
    implementation(Dependencies.Koin.KOIN_SCOPE)

    implementation(project(Modules.Libraries.CORE))
    implementation(project(Modules.Libraries.DATABASE))

    implementation(project(Modules.Shared.Settings.DOMAIN))
}
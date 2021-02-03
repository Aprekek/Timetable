plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
}

dependencies {
    implementation(Dependencies.Kotlin.KOTLIN_STANDARD_LIBRARY)
    implementation(Dependencies.Coroutine.COROUTINE_CORE)
    implementation(Dependencies.CORE)
    implementation(Dependencies.Lifecycle.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.WORKER)

    implementation(Dependencies.Koin.KOIN_CORE)
    implementation(Dependencies.Koin.KOIN_EXT)

    implementation(project(Modules.Shared.Notifications.DOMAIN))

    implementation(project(Modules.Shared.TimeUtils.UI))
    implementation(project(Modules.Shared.TimeUtils.DOMAIN))

    implementation(project(Modules.Shared.Settings.DOMAIN))
}
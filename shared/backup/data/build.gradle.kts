plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
}

dependencies {
    implementation(Dependencies.Kotlin.KOTLIN_STANDARD_LIBRARY)
    implementation(Dependencies.CORE)
    implementation(Dependencies.APP_COMPAT)

    implementation(Dependencies.Koin.KOIN_CORE)
    implementation(Dependencies.Koin.KOIN_EXT)

    implementation(Dependencies.Room.ROOM_COMPILER)
    implementation(Dependencies.Room.ROOM_KTX)
    kapt(Dependencies.Room.ROOM_COMPILER)

    implementation(project(Modules.Shared.Backup.DOMAIN))
    implementation(project(Modules.Shared.Lesson.DATA))
}
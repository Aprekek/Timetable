plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
}

dependencies {
    implementation(Dependencies.Kotlin.KOTLIN_STANDARD_LIBRARY)

    implementation(Dependencies.Coroutine.COROUTINE_CORE)

    implementation(Dependencies.Room.ROOM_RUNTIME)
    kapt(Dependencies.Room.ROOM_COMPILER)
    implementation(Dependencies.Room.ROOM_KTX)

    implementation(Dependencies.Koin.KOIN_CORE)
    implementation(Dependencies.Koin.KOIN_EXT)

    implementation(project(Modules.Shared.Lesson.DOMAIN))

    androidTestImplementation(Dependencies.Test.JUNIT)
    androidTestImplementation(Dependencies.Test.ANDROIDX_TEST_EXT)
    androidTestImplementation(Dependencies.Test.ANDROIDX_CORE)
    androidTestImplementation(Dependencies.Test.ANDROIDX_RUNNER)
    androidTestImplementation(Dependencies.Test.ANDROIDX_RULES)
}
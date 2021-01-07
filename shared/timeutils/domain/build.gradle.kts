plugins {
    id(Plugins.KOTLIN)
}

dependencies {
    implementation(Dependencies.Kotlin.KOTLIN_STANDARD_LIBRARY)
    implementation(Dependencies.Coroutine.COROUTINE_CORE)
    implementation(Dependencies.Koin.KOIN_CORE)

    testImplementation(Dependencies.Test.MOCKK)
    testImplementation(Dependencies.Test.JUNIT)
}
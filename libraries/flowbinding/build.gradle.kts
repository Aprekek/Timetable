plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
}

dependencies {
    implementation(Dependencies.Kotlin.KOTLIN_STANDARD_LIBRARY)
    implementation(Dependencies.Coroutine.COROUTINE_CORE)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CORE)
    implementation(Dependencies.Lifecycle.LIFECYCLE_RUNTIME)
}
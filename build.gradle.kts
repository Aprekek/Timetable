buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Dependencies.GRADLE)
        classpath(Dependencies.Kotlin.KOTLIN)
        classpath(Dependencies.Google.GOOGLE_SERVICES)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

subProjects {
    this.configure()
}

fun subProjects(vararg folders: Any, action: Action<in Project>) {
    folders.forEach { if (project.path == it.toString()) return }
    subprojects(action)
}
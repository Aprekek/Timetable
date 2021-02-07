object Dependencies {

    private object Versions {

        const val KOTLIN =
            "1.4.21"//https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-gradle-plugin
        const val GRADLE =
            "4.1.1"//https://mvnrepository.com/artifact/com.android.tools.build/gradle?repo=google
        const val GOOGLE =
            "4.3.4"//https://mvnrepository.com/artifact/com.google.gms/google-services?repo=google
        const val FIREBASE =
            "18.0.0"//https://mvnrepository.com/artifact/com.google.firebase/firebase-analytics
        const val DECORO = "1.5.0"//https://github.com/TinkoffCreditSystems/decoro
        const val LIFECYCLE =
            "2.2.0"//https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-extensions
        const val MATERIAL =
            "1.2.1"//https://mvnrepository.com/artifact/com.google.android.material/material
        const val CONSTRAINT_LAYOUT =
            "2.0.4"//https://mvnrepository.com/artifact/androidx.constraintlayout/constraintlayout
        const val CORE = "1.3.2"//https://mvnrepository.com/artifact/androidx.core/core-ktx
        const val APP_COMPAT =
            "1.2.0"//https://mvnrepository.com/artifact/androidx.appcompat/appcompat
        const val ACTIVITY =
            "1.1.0"//https://mvnrepository.com/artifact/androidx.activity/activity-ktx
        const val VIEW_PAGER_2 =
            "1.0.0"//https://mvnrepository.com/artifact/androidx.viewpager2/viewpager2
        const val FRAGMENT =
            "1.2.5"//https://mvnrepository.com/artifact/androidx.fragment/fragment-ktx
        const val SWIPE_REFRESH_LAYOUT =
            "1.1.0"//https://mvnrepository.com/artifact/androidx.swiperefreshlayout/swiperefreshlayout
        const val ROOM = "2.2.6"//https://mvnrepository.com/artifact/androidx.room/room-runtime
        const val COROUTINE =
            "1.4.2"//https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
        const val KOIN = "2.2.1"//https://mvnrepository.com/artifact/org.koin/koin-core?repo=jcenter
        const val NAVIGATION =
            "2.3.2"//https://mvnrepository.com/artifact/androidx.navigation/navigation-fragment-ktx
        const val DATABINDING = "4.1.1"
        const val JUNIT = "4.13.1"//https://mvnrepository.com/artifact/junit/junit
        const val MOCKK = "1.10.4"//https://mvnrepository.com/artifact/io.mockk/mockk
        const val ANDROIDX_TEST_EXT =
            "1.1.2"//https://mvnrepository.com/artifact/androidx.test.ext/junit
        const val ANDROIDX_TEST = "1.3.0"//https://mvnrepository.com/artifact/androidx.test/core
        const val WORKER =
            "2.5.0"//https://mvnrepository.com/artifact/androidx.work/work-runtime-ktx
        const val GUAVA =
            "30.1-jre"//https://mvnrepository.com/artifact/com.google.guava/guava
    }

    const val GRADLE = "com.android.tools.build:gradle:${Versions.GRADLE}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val CORE = "androidx.core:core-ktx:${Versions.CORE}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val ACTIVITY = "androidx.activity:activity-ktx:${Versions.ACTIVITY}"
    const val FRAGMENT = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT}"
    const val VIEW_PAGER_2 = "androidx.viewpager2:viewpager2:${Versions.VIEW_PAGER_2}"
    const val SWIPE_REFRESH_LAYOUT =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.SWIPE_REFRESH_LAYOUT}"
    const val DATABINDING = "com.android.databinding:compiler:${Versions.DATABINDING}"
    const val WORKER = "androidx.work:work-runtime-ktx:${Versions.WORKER}"
    const val GUAVA = "com.google.guava:guava:${Versions.GUAVA}"

    object Google {

        const val GOOGLE_SERVICES = "com.google.gms:google-services:${Versions.GOOGLE}"
        const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics:${Versions.FIREBASE}"
    }

    object Tinkoff {

        const val DECORO = "ru.tinkoff.decoro:decoro:${Versions.DECORO}"
    }

    object Kotlin {

        const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
        const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${Versions.KOTLIN}"
        const val KOTLIN_STANDARD_LIBRARY =
            "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
    }

    object Lifecycle {

        const val LIFECYCLE_EXTENSIONS =
            "androidx.lifecycle:lifecycle-extensions:${Versions.LIFECYCLE}"
        const val LIFECYCLE_RUNTIME =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
        const val LIFECYCLE_LIVEDATA =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}"
        const val LIFECYCLE_COMMON_JAVA8 =
            "androidx.lifecycle:lifecycle-common-java8:${Versions.LIFECYCLE}"
        const val LIFECYCLE_VIEWMODEL_KTX =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
    }

    object Room {

        const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
        const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
        const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"
    }

    object Navigation {

        const val NAVIGATION_FRAGMENT_KTX =
            "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
        const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
        const val NAVIGATION_SAVE_ARGS =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION}"
    }

    object Coroutine {

        const val COROUTINE_CORE =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINE}"
        const val COROUTINE_ANDROID =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINE}"
    }

    object Koin {

        const val KOIN_CORE = "org.koin:koin-core:${Versions.KOIN}"
        const val KOIN_SCOPE = "org.koin:koin-androidx-scope:${Versions.KOIN}"
        const val KOIN_VIEWMODEL = "org.koin:koin-androidx-viewmodel:${Versions.KOIN}"
        const val KOIN_EXT = "org.koin:koin-androidx-ext:${Versions.KOIN}"
    }

    object Test {

        const val JUNIT = "junit:junit:${Versions.JUNIT}"
        const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
        const val ANDROIDX_TEST_EXT = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST_EXT}"
        const val ANDROIDX_CORE = "androidx.test:core:${Versions.ANDROIDX_TEST}"
        const val ANDROIDX_RUNNER = "androidx.test:runner:${Versions.ANDROIDX_TEST}"
        const val ANDROIDX_RULES = "androidx.test:rules:${Versions.ANDROIDX_TEST}"
    }
}
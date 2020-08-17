package ru.fevgenson.timetable;

public class Dependencies {

    public static final String GRADLE = "com.android.tools.build:gradle:" + Versions.GRADLE;
    public static final String MATERIAL = "com.google.android.material:material:" + Versions.MATERIAL;
    public static final String CORE = "androidx.core:core-ktx:" + Versions.CORE;
    public static final String CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:" + Versions.CONSTRAINT_LAYOUT;
    public static final String APP_COMPAT = "androidx.appcompat:appcompat:" + Versions.APP_COMPAT;
    public static final String ACTIVITY = "androidx.activity:activity-ktx:" + Versions.ACTIVITY;
    public static final String FRAGMENT = "androidx.fragment:fragment-ktx:" + Versions.FRAGMENT;
    public static final String RECYCLE_VIEW = "androidx.recyclerview:recyclerview:" + Versions.RECYCLE_VIEW;
    public static final String SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:" + Versions.SWIPE_REFRESH_LAYOUT;
    public static final String DATABINDING = "com.android.databinding:compiler:" + Versions.DATABINDING;

    public static class Versions {
        public static final String KOTLIN = "1.3.72";
        public static final String GRADLE = "4.0.0";
        public static final String GOOGLE = "4.3.3";
        public static final String FIREBASE = "17.2.2";
        public static final String DECORO = "1.4.1";

        public static final String LIFECYCLE = "2.2.0";
        public static final String MATERIAL = "1.2.0-beta01";
        public static final String CONSTRAINT_LAYOUT = "1.1.3";
        public static final String CORE = "1.3.0";
        public static final String APP_COMPAT = "1.1.0";
        public static final String ACTIVITY = "1.1.0";
        public static final String FRAGMENT = "1.2.4";
        public static final String RECYCLE_VIEW = "1.1.0";
        public static final String SWIPE_REFRESH_LAYOUT = "1.0.0";
        public static final String ROOM = "2.2.5";
        public static final String COROUTINE = "1.3.7";
        public static final String KOIN = "2.1.5";
        public static final String NAVIGATION = "2.3.0";

        public static final String DATABINDING = "3.1.4";
    }

    public static class GOOGLE {
        public static final String GOOGLE_SERVICES = "com.google.gms:google-services:" + Versions.GOOGLE;
        public static final String FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics:" + Versions.FIREBASE;
    }

    public static class TINKOFF {
        public static final String DECORO = "ru.tinkoff.decoro:decoro:" + Versions.DECORO;
    }

    public static class Kotlin {
        public static final String KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:" + Versions.KOTLIN;
        public static final String KOTLIN_STANDARD_LIBRARY = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:" + Versions.KOTLIN;
    }

    public static class Lifecycle {
        public static final String LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:" + Versions.LIFECYCLE;
        public static final String LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime:" + Versions.LIFECYCLE;
        public static final String LIFECYCLE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:" + Versions.LIFECYCLE;
        public static final String LIFECYCLE_COMMON_JAVA8 = "androidx.lifecycle:lifecycle-common-java8:" + Versions.LIFECYCLE;
        public static final String LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:" + Versions.LIFECYCLE;
    }

    public static class Room {
        public static final String ROOM_RUNTIME = "androidx.room:room-runtime:" + Versions.ROOM;
        public static final String ROOM_COMPILER = "androidx.room:room-compiler:" + Versions.ROOM;
        public static final String ROOM_KTX = "androidx.room:room-ktx:" + Versions.ROOM;
    }

    public static class Navigation {
        public static final String NAVIGATION_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:" + Versions.NAVIGATION;
        public static final String NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:" + Versions.NAVIGATION;
        public static final String NAVIGATION_SAVE_ARGS = "androidx.navigation:navigation-safe-args-gradle-plugin:" + Versions.NAVIGATION;
    }

    public static class Coroutine {
        public static final String COROUTINE_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:" + Versions.COROUTINE;
        public static final String COROUTINE_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:" + Versions.COROUTINE;
    }

    public static class Koin {
        public static final String KOIN_CORE = "org.koin:koin-core:" + Versions.KOIN;
        public static final String KOIN_SCOPE = "org.koin:koin-androidx-scope:" + Versions.KOIN;
        public static final String KOIN_VIEWMODEL = "org.koin:koin-androidx-viewmodel:" + Versions.KOIN;
        public static final String KOIN_EXT = "org.koin:koin-androidx-ext:" + Versions.KOIN;
    }
}
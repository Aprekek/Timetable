package ru.fevgenson.timetable;

public class Dependencies {

    public static String GRADLE = "com.android.tools.build:gradle:" + Versions.GRADLE;
    public static String MATERIAL = "com.google.android.material:material:" + Versions.MATERIAL;
    public static String CORE = "androidx.core:core-ktx:" + Versions.CORE;
    public static String CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:" + Versions.CONSTRAINT_LAYOUT;
    public static String APP_COMPAT = "androidx.appcompat:appcompat:" + Versions.APP_COMPAT;
    public static String ACTIVITY = "androidx.activity:activity-ktx:" + Versions.ACTIVITY;
    public static String FRAGMENT = "androidx.fragment:fragment-ktx:" + Versions.FRAGMENT;
    public static String RECYCLE_VIEW = "androidx.recyclerview:recyclerview:" + Versions.RECYCLE_VIEW;
    public static String SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:" + Versions.SWIPE_REFRESH_LAYOUT;
    public static String DATABINDING = "com.android.databinding:compiler:" + Versions.DATABINDING;

    public static class Versions {
        public static String KOTLIN = "1.3.72";
        public static String GRADLE = "4.0.0";

        public static String LIFECYCLE = "2.2.0";
        public static String MATERIAL = "1.2.0";
        public static String CONSTRAINT_LAYOUT = "1.1.3";
        public static String CORE = "1.3.0";
        public static String APP_COMPAT = "1.1.0";
        public static String ACTIVITY = "1.1.0";
        public static String FRAGMENT = "1.2.4";
        public static String RECYCLE_VIEW = "1.1.0";
        public static String SWIPE_REFRESH_LAYOUT = "1.0.0";
        public static String ROOM = "2.2.5";
        public static String COROUTINE = "1.3.7";
        public static String KOIN = "2.1.5";
        public static String NAVIGATION = "2.3.0";

        public static String DATABINDING = "3.1.4";
    }

    public static class Kotlin {
        public static String KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:" + Versions.KOTLIN;
        public static String KOTLIN_STANDARD_LIBRARY = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:" + Versions.KOTLIN;
    }

    public static class Lifecycle {
        public static String LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:" + Versions.LIFECYCLE;
        public static String LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime:" + Versions.LIFECYCLE;
        public static String LIFECYCLE_COMMON_JAVA8 = "androidx.lifecycle:lifecycle-common-java8:" + Versions.LIFECYCLE;
        public static String LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:" + Versions.LIFECYCLE;
    }

    public static class Room {
        public static String ROOM_RUNTIME = "androidx.room:room-runtime:" + Versions.ROOM;
        public static String ROOM_COMPILER = "androidx.room:room-compiler:" + Versions.ROOM;
        public static String ROOM_KTX = "androidx.room:room-ktx:" + Versions.ROOM;
    }

    public static class Navigation {
        public static String NAVIGATION_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:" + Versions.NAVIGATION;
        public static String NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:" + Versions.NAVIGATION;
        public static String NAVIGATION_SAVE_ARGS = "androidx.navigation:navigation-safe-args-gradle-plugin:" + Versions.NAVIGATION;
    }

    public static class Coroutine {
        public static String COROUTINE_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:" + Versions.COROUTINE;
        public static String COROUTINE_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:" + Versions.COROUTINE;
    }

    public static class Koin {
        public static String KOIN_CORE = "org.koin:koin-core:" + Versions.KOIN;
    }
}
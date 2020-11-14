package ru.fevgenson.timetable.libraries.flowbinding

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun ViewPager2.pageBind(
    flow: Flow<Int>,
    lifecycleOwner: LifecycleOwner,
    smoothScroll: Boolean = true
) {
    flow.onEach {
        setCurrentItem(it, smoothScroll)
    }.launchIn(lifecycleOwner.lifecycle.coroutineScope)
}
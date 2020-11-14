package ru.fevgenson.timetable.libraries.flowbinding

import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun TextView.textBind(flow: Flow<String>, lifecycleOwner: LifecycleOwner) {
    flow.onEach { text = it }.launchIn(lifecycleOwner.lifecycle.coroutineScope)
}

fun TextView.textResBind(flow: Flow<Int>, lifecycleOwner: LifecycleOwner) {
    flow.onEach { setText(it) }.launchIn(lifecycleOwner.lifecycle.coroutineScope)
}

fun TextView.bindTextOrGone(flow: Flow<String?>, lifecycleOwner: LifecycleOwner) {
    flow.onEach {
        text = it
        isVisible = it != null
    }.launchIn(lifecycleOwner.lifecycle.coroutineScope)
}

fun TextView.bindTextResOrGone(flow: Flow<Int?>, lifecycleOwner: LifecycleOwner) {
    flow.onEach { textRes ->
        textRes?.let { setText(it) }
        isVisible = textRes != null
    }.launchIn(lifecycleOwner.lifecycle.coroutineScope)
}
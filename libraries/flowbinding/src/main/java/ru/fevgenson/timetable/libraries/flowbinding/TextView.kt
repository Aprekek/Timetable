package ru.fevgenson.timetable.libraries.flowbinding

import android.widget.TextView
import androidx.core.view.isVisible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun TextView.textBind(flow: Flow<String>, coroutineScope: CoroutineScope) {
    flow.onEach { text = it }.launchIn(coroutineScope)
}

fun TextView.textResBind(flow: Flow<Int>, coroutineScope: CoroutineScope) {
    flow.onEach { setText(it) }.launchIn(coroutineScope)
}

fun TextView.bindTextOrGone(flow: Flow<String?>, coroutineScope: CoroutineScope) {
    flow.onEach {
        text = it
        isVisible = it != null
    }.launchIn(coroutineScope)
}

fun TextView.bindTextResOrGone(flow: Flow<Int?>, coroutineScope: CoroutineScope) {
    flow.onEach { textRes ->
        textRes?.let { setText(it) }
        isVisible = textRes != null
    }.launchIn(coroutineScope)
}
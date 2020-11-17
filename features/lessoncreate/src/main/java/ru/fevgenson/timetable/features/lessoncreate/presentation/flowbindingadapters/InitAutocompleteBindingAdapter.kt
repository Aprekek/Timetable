package ru.fevgenson.timetable.features.lessoncreate.presentation.flowbindingadapters

import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.core.view.isVisible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.fevgenson.timetable.libraries.database.data.tables.TeacherEntity

private const val MIN_THRESHOLD = 1

fun AutoCompleteTextView.setupData(
    data: Flow<List<String>>,
    coroutineScope: CoroutineScope
) {
    data.onEach { autocomplete ->
        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_dropdown_item,
            autocomplete
        )
        threshold = MIN_THRESHOLD
        setAdapter(adapter)
    }.launchIn(coroutineScope)
}

fun AutoCompleteTextView.setupTeacherData(
    data: Flow<List<TeacherEntity>>,
    coroutineScope: CoroutineScope
) {
    data.onEach { autocomplete ->
        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_dropdown_item,
            autocomplete.map { it.name }
        )
        threshold = MIN_THRESHOLD
        setAdapter(adapter)
    }.launchIn(coroutineScope)
}

fun AutoCompleteTextView.setupStartingSlots(slots: Int) {
    setOnTouchListener { view: View, motionEvent: MotionEvent ->
        view.performClick()
        if (text?.isEmpty() == true) {
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> setText(slots)
            }
        }
        return@setOnTouchListener false
    }
}

fun Button.setAutocompleteState(
    data: Flow<List<String>>,
    coroutineScope: CoroutineScope
) {
    data.onEach {
        isVisible = it.isNotEmpty()
    }.launchIn(coroutineScope)
}
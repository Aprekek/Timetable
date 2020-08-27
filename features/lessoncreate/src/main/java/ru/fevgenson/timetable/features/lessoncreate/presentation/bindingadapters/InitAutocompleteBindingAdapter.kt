package ru.fevgenson.timetable.features.lessoncreate.presentation.bindingadapters

import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.databinding.BindingAdapter
import ru.fevgenson.timetable.libraries.database.data.tables.TeacherEntity

private const val MIN_THRESHOLD = 1

@BindingAdapter("autocompleteData")
fun AutoCompleteTextView.setupData(data: List<String>?) {
    data?.let {
        val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, data)
        threshold = MIN_THRESHOLD
        setAdapter(adapter)
    }
}

@BindingAdapter("teacherAutocompleteData")
fun AutoCompleteTextView.setupTeacherData(data: List<TeacherEntity>?) {
    data?.let {
        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_dropdown_item,
            data.map { it.name }
        )
        threshold = MIN_THRESHOLD
        setAdapter(adapter)
    }
}

@BindingAdapter("stateAutocompleteData")
fun Button.setAutocompleteState(data: List<String>?) {
    visibility = when {
        data.isNullOrEmpty() -> View.GONE
        else -> View.VISIBLE
    }
}
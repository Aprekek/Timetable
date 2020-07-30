package ru.fevgenson.timetable.libraries.core.presentation.bindingadapters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("textOrGone")
fun TextView.setTextOrGone(text: String?) {
    this.text = text
    visibility = if (text == null) View.GONE else View.VISIBLE
}
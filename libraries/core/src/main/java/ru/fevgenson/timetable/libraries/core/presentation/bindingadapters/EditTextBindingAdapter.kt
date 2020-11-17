package ru.fevgenson.timetable.libraries.core.presentation.bindingadapters

import android.view.MotionEvent
import android.view.View
import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter

@Deprecated("юзаяем flow")
@BindingAdapter("startingSlots")
fun AutoCompleteTextView.setupStartingSlots(slots: String) {
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
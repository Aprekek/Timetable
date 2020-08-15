package ru.fevgenson.timetable.libraries.core.presentation.bindingadapters

import android.view.MotionEvent
import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("startingSlots")
fun TextInputEditText.setupStartingSlots(slots: String) {
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
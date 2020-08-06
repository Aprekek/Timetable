package ru.fevgenson.timetable.features.lessoncreate.presentation.bindingadapters

import android.view.MotionEvent
import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("startingPhoneSlots")
fun TextInputEditText.setStartingPhoneSlots(slots: String) {
    setOnTouchListener { view: View, motionEvent: MotionEvent ->
        view.performClick()
        if (text?.isEmpty()!!) {
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> setText(slots)
            }
        }
        return@setOnTouchListener false
    }
}
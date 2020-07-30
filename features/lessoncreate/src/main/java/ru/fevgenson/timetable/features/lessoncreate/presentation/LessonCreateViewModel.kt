package ru.fevgenson.timetable.features.lessoncreate.presentation

import android.util.Log
import androidx.lifecycle.ViewModel

class LessonCreateViewModel : ViewModel() {

    fun onClearItemClick() {
        Log.d("LessonCreateViewModel", "Button was clicked")
    }
}
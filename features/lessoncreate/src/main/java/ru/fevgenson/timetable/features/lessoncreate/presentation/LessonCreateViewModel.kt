package ru.fevgenson.timetable.features.lessoncreate.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LessonCreateViewModel : ViewModel() {

    private val _currentPage: MutableLiveData<Int> = MutableLiveData(0)
    val currentPage: LiveData<Int>
        get() = _currentPage

    fun onTopBackButtonClick() {
        if (_currentPage.value == 0) {
            Log.d("LessonCreateViewModel", "Exit")
        } else {
            _currentPage.value = currentPage.value?.minus(1)
        }
    }

    fun onNextButtonClick() {
        if (_currentPage.value!! > 1) {
            Log.d("LessonCreateViewModel", "Exit")
        } else {
            _currentPage.value = currentPage.value?.plus(1)
        }
    }

    fun onClearItemClick() {
        Log.d("LessonCreateViewModel", "Button was clicked")
    }
}
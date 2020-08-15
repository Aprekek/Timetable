package ru.fevgenson.timetable.libraries.core.presentation.bindingadapters

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorMessage")
fun TextInputLayout.showError(errorMessage: Int?) {
    if (errorMessage != null) {
        error = context.getString(errorMessage)
        isErrorEnabled = true
    } else {
        error = null
        isErrorEnabled = false
    }
}
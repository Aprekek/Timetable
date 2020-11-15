package ru.fevgenson.timetable.libraries.flowbinding

import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun TextInputLayout.showError(errorMessage: Flow<Int?>, coroutineScope: CoroutineScope) {
    errorMessage.onEach {
        if (it != null) {
            error = resources.getString(it)
            isErrorEnabled = true
        } else {
            error = null
            isErrorEnabled = false
        }
    }.launchIn(coroutineScope)
}
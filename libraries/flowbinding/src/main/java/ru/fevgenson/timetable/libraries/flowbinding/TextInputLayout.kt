package ru.fevgenson.timetable.libraries.flowbinding

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun TextInputLayout.showError(errorMessage: Flow<Int?>, lifecycleOwner: LifecycleOwner) {
    errorMessage.onEach {
        if (it != null) {
            error = resources.getString(it)
            isErrorEnabled = true
        } else {
            error = null
            isErrorEnabled = false
        }
    }.launchIn(lifecycleOwner.lifecycle.coroutineScope)
}
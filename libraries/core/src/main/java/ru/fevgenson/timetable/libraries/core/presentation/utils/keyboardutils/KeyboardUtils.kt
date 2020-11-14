package ru.fevgenson.timetable.libraries.core.presentation.utils.keyboardutils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.closeKeyboard(view: View) {
    val inputManager =
        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputManager?.hideSoftInputFromWindow(view.applicationWindowToken, 0)
}

fun requestKeyboardUseCase(context: Context, view: View) {
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputManager?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}
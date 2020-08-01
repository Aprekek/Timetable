package ru.fevgenson.timetable.libraries.core.presentation.utils.keyboardutils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

fun Fragment.closeKeyboard(binding: ViewDataBinding) {
    val inputManager =
        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputManager?.hideSoftInputFromWindow(binding.root.applicationWindowToken, 0)
}

package ru.fevgenson.timetable.libraries.core.presentation.bindingadapters

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.textfield.TextInputLayout
import ru.fevgenson.timetable.libraries.core.presentation.utils.keyboardutils.requestKeyboardUseCase

@BindingAdapter("errorMessage", "requestError")
fun TextInputLayout.showError(errorMessage: String, requireError: Boolean) {
    editText?.let {
        error = if (requireError && it.text.isEmpty()) {
            requestFocus()
            requestKeyboardUseCase(context, it)
            errorMessage
        } else
            null
    }
}

@InverseBindingAdapter(attribute = "requestError", event = "requestErrorAttrChanged")
fun TextInputLayout.getRequireError() = editText?.text?.isEmpty()

@BindingAdapter("requestErrorAttrChanged", requireAll = false)
fun TextInputLayout.textChangedListener(inverseBindingListener: InverseBindingListener) {
    editText?.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            inverseBindingListener.onChange()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    })
}
package ru.fevgenson.timetable.features.settings.presentation.style.bindingadapters

import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import ru.fevgenson.timetable.features.settings.R

@BindingAdapter("theme")
fun RadioGroup.selectTheme(theme: Int) {
    val radioButton: RadioButton = when (theme) {
        AppCompatDelegate.MODE_NIGHT_YES -> findViewById(R.id.dark_theme)
        AppCompatDelegate.MODE_NIGHT_NO -> findViewById(R.id.light_theme)
        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> findViewById(R.id.system_theme)
        else -> throw IllegalArgumentException("wrong day night constant $theme")
    }
    radioButton.isChecked = true
}

@InverseBindingAdapter(attribute = "theme", event = "checkChangeListener")
fun RadioGroup.getTheme(): Int = when (checkedRadioButtonId) {
    R.id.dark_theme -> AppCompatDelegate.MODE_NIGHT_YES
    R.id.light_theme -> AppCompatDelegate.MODE_NIGHT_NO
    R.id.system_theme -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    else -> throw IllegalArgumentException("wrong radio button id $checkedRadioButtonId")
}

@BindingAdapter("checkChangeListener")
fun RadioGroup.initListener(inverseBindingListener: InverseBindingListener) {
    setOnCheckedChangeListener { _, _ -> inverseBindingListener.onChange() }
}
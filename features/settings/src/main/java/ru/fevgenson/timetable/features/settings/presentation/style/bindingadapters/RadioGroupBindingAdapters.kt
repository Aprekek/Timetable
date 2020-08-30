package ru.fevgenson.timetable.features.settings.presentation.style.bindingadapters

import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import ru.fevgenson.timetable.features.settings.R
import ru.fevgenson.timetable.libraries.database.domain.repository.SettingsRepository

@BindingAdapter("theme")
fun RadioGroup.selectTheme(theme: Int) {
    (getChildAt(theme) as? RadioButton)?.isChecked = true
}

@InverseBindingAdapter(attribute = "theme", event = "checkChangeListener")
fun RadioGroup.getTheme(): Int = when (checkedRadioButtonId) {
    R.id.dark_theme -> SettingsRepository.DARK
    R.id.light_theme -> SettingsRepository.LIGHT
    R.id.system_theme -> SettingsRepository.SYSTEM
    else -> throw IllegalArgumentException("wrong radio button id $checkedRadioButtonId")
}

@BindingAdapter("checkChangeListener")
fun RadioGroup.initListener(inverseBindingListener: InverseBindingListener) {
    setOnCheckedChangeListener { _, _ -> inverseBindingListener.onChange() }
}
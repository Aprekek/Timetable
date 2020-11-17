package ru.fevgenson.timetable.libraries.core.presentation.bindingadapters

import android.view.View
import androidx.databinding.BindingAdapter

@Deprecated("юзаяем flow")
@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}
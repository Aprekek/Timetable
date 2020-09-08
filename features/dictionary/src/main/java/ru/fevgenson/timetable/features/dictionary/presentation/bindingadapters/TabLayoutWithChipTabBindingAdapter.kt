package ru.fevgenson.timetable.features.dictionary.presentation.bindingadapters

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import com.google.android.material.tabs.TabLayout

@BindingAdapter(
    "tabMarginDpHorizontal",
    "tabMarginDpVertical"
)
fun TabLayout.setTabMargins(
    tabMarginDpHorizontal: Float,
    tabMarginDpVertical: Float
) {

    for (i in 0 until tabCount) {
        val tab = (getChildAt(0) as ViewGroup).getChildAt(i)
        val tabParameters = tab.layoutParams as ViewGroup.MarginLayoutParams
        tabParameters.setMargins(
            tabMarginDpHorizontal.toInt(),
            tabMarginDpVertical.toInt(),
            tabMarginDpHorizontal.toInt(),
            tabMarginDpVertical.toInt()
        )
        tab.requestLayout()
    }
}
package ru.fevgenson.timetable.features.lessoncreate.presentation.bindingadapters

import androidx.core.view.children
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingAdapter("checkedChips")
fun ChipGroup.setStarterChips(checkedChips: List<Boolean>?) {
    checkedChips?.let {
        children.forEachIndexed { index, view ->
            (view as? Chip)?.apply {
                isChecked = it[index]
            }
        }
    }
}

@InverseBindingAdapter(attribute = "checkedChips", event = "checkedChipsAttrChanged")
fun ChipGroup.getChips(): List<Boolean> = mutableListOf<Boolean>().apply {
    children.forEach {
        add((it as? Chip)?.isChecked ?: false)
    }
}

@BindingAdapter("checkedChipsAttrChanged", requireAll = false)
fun ChipGroup.checkedChipsListener(inverseBindingListener: InverseBindingListener) {
    children.forEach {
        it.setOnClickListener {
            inverseBindingListener.onChange()
        }
    }
}
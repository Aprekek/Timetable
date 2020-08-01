package ru.fevgenson.timetable.features.lessoncreate.presentation.bindingadapters

import android.widget.ImageView
import android.widget.PopupMenu
import androidx.databinding.BindingAdapter

@BindingAdapter("menuRes", "menuItemClickListener")
fun ImageView.initMenu(menuRes: Int, listener: () -> Unit) {
    setOnClickListener {
        PopupMenu(context, this).apply {
            inflate(menuRes)
            setOnMenuItemClickListener {
                listener()
                return@setOnMenuItemClickListener false
            }
            show()
        }
    }
}
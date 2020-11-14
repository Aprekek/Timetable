package ru.fevgenson.timetable.features.lessoncreate.presentation.flowbindingadapters

import android.widget.ImageView
import android.widget.PopupMenu

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
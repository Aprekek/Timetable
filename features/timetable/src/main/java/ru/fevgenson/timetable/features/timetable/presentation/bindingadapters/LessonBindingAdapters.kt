package ru.fevgenson.timetable.features.timetable.presentation.bindingadapters

import android.view.Gravity
import android.view.View
import android.widget.PopupMenu
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import ru.fevgenson.timetable.features.timetable.R

@BindingAdapter("type", "teacher")
fun TextView.setTypeAndTeacher(type: String?, teacher: String?) {
    when {
        type != null && teacher != null -> {
            text = context.getString(
                R.string.timetable_mask_with_brackets,
                type,
                teacher
            )
            visibility = View.VISIBLE
        }
        type != null -> {
            text = type
            visibility = View.VISIBLE
        }
        teacher != null -> {
            text = teacher
            visibility = View.VISIBLE
        }
        else -> {
            visibility = View.GONE
        }
    }
}

@BindingAdapter("housing", "classroom")
fun TextView.setHousingAndClassroom(housing: String?, classroom: String?) {
    when {
        housing != null && classroom != null -> {
            text = context.getString(
                R.string.timetable_mask_with_comma,
                housing,
                classroom
            )
            visibility = View.VISIBLE
        }
        housing != null -> {
            text = housing
            visibility = View.VISIBLE
        }
        classroom != null -> {
            text = classroom
            visibility = View.VISIBLE
        }
        else -> {
            visibility = View.GONE
        }
    }
}

@BindingAdapter(
    "editMenuListener",
    "copyMenuListener",
    "deleteMenuListener",
    requireAll = false
)
fun CardView.initPopUpMenu(
    editMenuListener: View.OnClickListener,
    copyMenuListener: View.OnClickListener,
    deleteMenuListener: View.OnClickListener
) {
    setOnLongClickListener {
        PopupMenu(context, this, Gravity.END).apply {
            inflate(R.menu.menu_lesson)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.edit_lesson_item -> editMenuListener.onClick(this@initPopUpMenu)
                    R.id.copy_lesson_item -> copyMenuListener.onClick(this@initPopUpMenu)
                    R.id.delete_lesson_item -> deleteMenuListener.onClick(this@initPopUpMenu)
                }
                return@setOnMenuItemClickListener false
            }
        }.show()
        return@setOnLongClickListener true
    }
}
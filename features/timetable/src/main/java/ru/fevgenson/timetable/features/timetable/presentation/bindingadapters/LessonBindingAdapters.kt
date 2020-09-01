package ru.fevgenson.timetable.features.timetable.presentation.bindingadapters

import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import org.koin.java.KoinJavaComponent.get
import ru.fevgenson.timetable.features.timetable.R
import ru.fevgenson.timetable.libraries.core.utils.broadcastrecivers.DateBroadcastReceiver
import ru.fevgenson.timetable.libraries.core.utils.broadcastrecivers.MinutesBroadcastReceiver
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils
import ru.fevgenson.timetable.libraries.core.utils.dateutils.ShowedTimeUtils

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

@BindingAdapter(
    "currentWeek",
    "currentDay",
    "timeDiapason",
    "lifecycleOwner"
)
fun ImageView.initCurrentLessonObserver(
    currentWeek: Int,
    currentDay: Int,
    timeDiapason: String,
    lifecycleOwner: LifecycleOwner
) {
    var itsToday = DateUtils.getCurrentDay() == currentDay &&
            DateUtils.getCurrentWeek() == currentWeek
    setTimeState(itsToday, timeDiapason)

    val dateBroadcastReceiver = get(DateBroadcastReceiver::class.java)
    dateBroadcastReceiver.callbacks.observe(lifecycleOwner) {
        itsToday = it.day == currentDay && it.weekType == currentWeek
        setTimeState(itsToday, timeDiapason)
    }
    val minutesBroadcastReceiver = get(MinutesBroadcastReceiver::class.java)
    minutesBroadcastReceiver.callbacks.observe(lifecycleOwner) {
        setTimeState(itsToday, timeDiapason)
    }
}

@BindingAdapter(
    "currentWeek",
    "currentDay",
    "timeDiapason",
    "lifecycleOwner"
)
fun TextView.initTimeObserver(
    currentWeek: Int,
    currentDay: Int,
    timeDiapason: String,
    lifecycleOwner: LifecycleOwner
) {
    var itsToday = DateUtils.getCurrentDay() == currentDay &&
            DateUtils.getCurrentWeek() == currentWeek
    var showedMinutesText = ShowedTimeUtils.getShowedMinutesText(
        lessonDiapason = timeDiapason,
        context = context
    )
    setTimeState(showedMinutesText, itsToday)

    val dateBroadcastReceiver = get(DateBroadcastReceiver::class.java)
    dateBroadcastReceiver.callbacks.observe(lifecycleOwner) {
        itsToday = it.day == currentDay && it.weekType == currentWeek
        showedMinutesText = ShowedTimeUtils.getShowedMinutesText(
            lessonDiapason = timeDiapason,
            context = context
        )
        setTimeState(showedMinutesText, itsToday)
    }
    val minutesBroadcastReceiver = get(MinutesBroadcastReceiver::class.java)
    minutesBroadcastReceiver.callbacks.observe(lifecycleOwner) {
        showedMinutesText = ShowedTimeUtils.getShowedMinutesText(
            lessonDiapason = timeDiapason,
            context = context
        )
        setTimeState(showedMinutesText, itsToday)
    }
}

private fun ImageView.setTimeState(
    itsToday: Boolean,
    timeDiapason: String
) {
    visibility = when {
        !itsToday -> View.GONE
        ShowedTimeUtils.currentTimeInThisDiapason(timeDiapason) -> View.VISIBLE
        else -> View.GONE
    }
}

private fun TextView.setTimeState(
    minutesText: String?,
    itsToday: Boolean
) {
    when {
        !itsToday -> visibility = View.GONE
        minutesText != null -> {
            text = minutesText
            visibility = View.VISIBLE
        }
        else -> visibility = View.GONE
    }
}
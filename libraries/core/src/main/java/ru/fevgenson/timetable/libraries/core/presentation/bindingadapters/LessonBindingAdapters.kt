package ru.fevgenson.timetable.libraries.core.presentation.bindingadapters

import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.cardview.widget.CardView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.java.KoinJavaComponent.get
import ru.fevgenson.timetable.libraries.core.R
import ru.fevgenson.timetable.libraries.core.utils.broadcastrecivers.DateBroadcastReceiver
import ru.fevgenson.timetable.libraries.core.utils.broadcastrecivers.MinutesBroadcastReceiver
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils
import ru.fevgenson.timetable.libraries.core.utils.dateutils.ShowedTimeUtils

//TODO: вынести это все в shared module
fun TextView.setTypeAndTeacher(type: String?, teacher: String?) {
    when {
        type != null && teacher != null -> {
            text = context.getString(
                R.string.core_mask_with_brackets,
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

fun TextView.setHousingAndClassroom(housing: String?, classroom: String?) {
    when {
        housing != null && classroom != null -> {
            text = context.getString(
                R.string.core_mask_with_comma,
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

fun ImageView.initCurrentLessonObserver(
    lessonWeek: Int,
    lessonDay: Int,
    timeDiapason: String,
    coroutineScope: CoroutineScope
) {
    var itsToday = DateUtils.getCurrentDay() == lessonDay &&
            DateUtils.getCurrentWeek() == lessonWeek
    setTimeState(itsToday, timeDiapason)

    val dateBroadcastReceiver = get(DateBroadcastReceiver::class.java)
    dateBroadcastReceiver.callbacksFlow.onEach {
        itsToday = it.day == lessonDay && it.weekType == lessonWeek
        setTimeState(itsToday, timeDiapason)
    }.launchIn(coroutineScope)
    val minutesBroadcastReceiver = get(MinutesBroadcastReceiver::class.java)
    minutesBroadcastReceiver.callbacksFlow.onEach {
        setTimeState(itsToday, timeDiapason)
    }.launchIn(coroutineScope)
}

fun TextView.initTimeObserver(
    lessonWeek: Int,
    lessonDay: Int,
    timeDiapason: String,
    coroutineScope: CoroutineScope
) {
    var itsToday = DateUtils.getCurrentDay() == lessonDay &&
            DateUtils.getCurrentWeek() == lessonWeek
    var showedMinutesText = ShowedTimeUtils.getShowedMinutesText(
        lessonDiapason = timeDiapason,
        context = context
    )
    setTimeState(showedMinutesText, itsToday)

    val dateBroadcastReceiver = get(DateBroadcastReceiver::class.java)
    dateBroadcastReceiver.callbacksFlow.onEach {
        itsToday = it.day == lessonDay && it.weekType == lessonWeek
        showedMinutesText = ShowedTimeUtils.getShowedMinutesText(
            lessonDiapason = timeDiapason,
            context = context
        )
        setTimeState(showedMinutesText, itsToday)
    }.launchIn(coroutineScope)
    val minutesBroadcastReceiver = get(MinutesBroadcastReceiver::class.java)
    minutesBroadcastReceiver.callbacksFlow.onEach {
        showedMinutesText = ShowedTimeUtils.getShowedMinutesText(
            lessonDiapason = timeDiapason,
            context = context
        )
        setTimeState(showedMinutesText, itsToday)
    }.launchIn(coroutineScope)
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
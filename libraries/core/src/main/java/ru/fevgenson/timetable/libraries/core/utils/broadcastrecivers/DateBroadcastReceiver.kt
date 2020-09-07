package ru.fevgenson.timetable.libraries.core.utils.broadcastrecivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils

//init в MainActivity
class DateBroadcastReceiver : BroadcastReceiver() {

    companion object {

        val dateBroadcastReceiverIntentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_DATE_CHANGED)
        }
    }

    data class WeekDayPair(
        val weekType: Int,
        val day: Int
    )

    private val _callbacks = MutableLiveData<WeekDayPair>()
    val callbacks: LiveData<WeekDayPair>
        get() = _callbacks

    override fun onReceive(p0: Context?, p1: Intent?) {
        _callbacks.value = WeekDayPair(
            weekType = DateUtils.getCurrentWeek(),
            day = DateUtils.getCurrentDay()
        )
    }
}
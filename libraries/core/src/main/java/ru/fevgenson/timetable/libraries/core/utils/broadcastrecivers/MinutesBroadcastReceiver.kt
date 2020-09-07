package ru.fevgenson.timetable.libraries.core.utils.broadcastrecivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.fevgenson.timetable.libraries.core.utils.dateutils.MyTimeUtils

//init в MainActivity
class MinutesBroadcastReceiver : BroadcastReceiver() {

    companion object {

        val minutesBroadcastReceiverIntentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_TIME_TICK)
        }
    }

    data class WeekDayPair(
        val weekType: Int,
        val day: Int
    )

    private val _callbacks = MutableLiveData<Int>()
    val callbacks: LiveData<Int>
        get() = _callbacks

    override fun onReceive(p0: Context?, p1: Intent?) {
        _callbacks.value = MyTimeUtils.getCurrentTime()
    }
}
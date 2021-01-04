package ru.fevgenson.timetable.libraries.core.utils.broadcastrecivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils

//init в MainActivity
@Deprecated("Используйте утилиты из shared модуля")
class DateBroadcastReceiver : BroadcastReceiver() {

    companion object {

        val dateBroadcastReceiverIntentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_DATE_CHANGED)
        }
    }

    data class WeekDayPair(
        val weekType: Int,
        val day: Int
    ) {
        companion object {

            fun newInstance(): WeekDayPair = WeekDayPair(
                weekType = DateUtils.getCurrentWeek(),
                day = DateUtils.getCurrentDay()
            )
        }
    }

    @Deprecated("Используй callbacksFlow")
    private val _callbacks = MutableLiveData<WeekDayPair>()
    val callbacks: LiveData<WeekDayPair>
        get() = _callbacks

    private val _callbacksFlow = MutableStateFlow(WeekDayPair.newInstance())
    val callbacksFlow: Flow<WeekDayPair>
        get() = _callbacksFlow

    override fun onReceive(p0: Context?, p1: Intent?) {
        val weekDayPair = WeekDayPair.newInstance()
        _callbacks.value = weekDayPair
        _callbacksFlow.value = weekDayPair
    }
}
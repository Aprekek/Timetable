package ru.fevgenson.timetable.shared.timeutils.ui.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.fevgenson.timetable.shared.timeutils.domain.entity.WeekAndDayPair
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentDayUseCase
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentWeekTypeUseCase

class DateBroadcastReceiver(
    private val getCurrentDayUseCase: GetCurrentDayUseCase,
    private val getCurrentWeekTypeUseCase: GetCurrentWeekTypeUseCase
) : BroadcastReceiver() {

    companion object {

        val dateBroadcastReceiverIntentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_DATE_CHANGED)
        }
    }

    private val _callbacksFlow = MutableStateFlow(createWeekDayPair())
    val callbacksFlow: Flow<WeekAndDayPair>
        get() = _callbacksFlow

    override fun onReceive(p0: Context?, p1: Intent?) {
        _callbacksFlow.value = createWeekDayPair()
    }

    private fun createWeekDayPair(): WeekAndDayPair =
        WeekAndDayPair(
            weekType = getCurrentWeekTypeUseCase(),
            day = getCurrentDayUseCase()
        )
}
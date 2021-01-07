package ru.fevgenson.timetable.shared.timeutils.ui.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentTimeUseCase

class MinutesBroadcastReceiver(
    private val getCurrentTimeUseCase: GetCurrentTimeUseCase
) : BroadcastReceiver() {

    companion object {

        val minutesBroadcastReceiverIntentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_TIME_TICK)
        }
    }

    private val _callbacksFlow = MutableStateFlow(getCurrentTimeUseCase())
    val callbacksFlow: Flow<Int>
        get() = _callbacksFlow

    override fun onReceive(p0: Context?, p1: Intent?) {
        _callbacksFlow.value = getCurrentTimeUseCase()
    }
}
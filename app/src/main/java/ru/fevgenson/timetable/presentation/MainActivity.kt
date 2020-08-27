package ru.fevgenson.timetable.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.get
import ru.fevgenson.timetable.R
import ru.fevgenson.timetable.libraries.core.utils.broadcastrecivers.DateBroadcastReceiver
import ru.fevgenson.timetable.libraries.core.utils.broadcastrecivers.MinutesBroadcastReceiver

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        subscribeBroadcastReceivers()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        unsubscribeBroadcastReceivers()
        super.onDestroy()
    }

    private fun subscribeBroadcastReceivers() {
        val dateBroadcastReceiver: DateBroadcastReceiver = get()
        val minutesBroadcastReceiver: MinutesBroadcastReceiver = get()
        registerReceiver(
            dateBroadcastReceiver,
            DateBroadcastReceiver.dateBroadcastReceiverIntentFilter
        )
        registerReceiver(
            minutesBroadcastReceiver,
            MinutesBroadcastReceiver.minutesBroadcastReceiverIntentFilter
        )
    }

    private fun unsubscribeBroadcastReceivers() {
        val dateBroadcastReceiver: DateBroadcastReceiver = get()
        val minutesBroadcastReceiver: MinutesBroadcastReceiver = get()
        unregisterReceiver(dateBroadcastReceiver)
        unregisterReceiver(minutesBroadcastReceiver)
    }
}
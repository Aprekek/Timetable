package ru.fevgenson.timetable.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.fevgenson.timetable.R
import ru.fevgenson.timetable.shared.notifications.ui.service.ForegroundNotificationService
import ru.fevgenson.timetable.shared.timeutils.ui.broadcastreceivers.DateBroadcastReceiver
import ru.fevgenson.timetable.shared.timeutils.ui.broadcastreceivers.MinutesBroadcastReceiver

//TODO: переехать на новые утилиты для времени после выноса биндинг адаптеров в shared модули
class MainActivity : AppCompatActivity() {

    private val dateBroadcastReceiver: DateBroadcastReceiver by inject()
    private val minutesBroadcastReceiver: MinutesBroadcastReceiver by inject()

    private val oldDateBroadcastReceiver by
    inject<ru.fevgenson.timetable.libraries.core.utils.broadcastrecivers.DateBroadcastReceiver>()
    private val oldMinutesBroadcastReceiver by
    inject<ru.fevgenson.timetable.libraries.core.utils.broadcastrecivers.MinutesBroadcastReceiver>()

    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeBroadcastReceivers()
        setupSettings()
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        unsubscribeBroadcastReceivers()
        super.onDestroy()
    }

    private fun setupSettings() {
        //выбираем тему
        AppCompatDelegate.setDefaultNightMode(viewModel.savedTheme)
        //запускаем обновляемое уведомление
        if (viewModel.foregroundServiceEnabled) {
            ForegroundNotificationService.startService(this)
        } else {
            ForegroundNotificationService.stopService(this)
        }
    }

    private fun subscribeBroadcastReceivers() {
        registerReceiver(
            oldDateBroadcastReceiver,
            DateBroadcastReceiver.dateBroadcastReceiverIntentFilter
        )
        registerReceiver(
            oldMinutesBroadcastReceiver,
            MinutesBroadcastReceiver.minutesBroadcastReceiverIntentFilter
        )
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
        unregisterReceiver(oldDateBroadcastReceiver)
        unregisterReceiver(oldMinutesBroadcastReceiver)
        unregisterReceiver(dateBroadcastReceiver)
        unregisterReceiver(minutesBroadcastReceiver)
    }
}
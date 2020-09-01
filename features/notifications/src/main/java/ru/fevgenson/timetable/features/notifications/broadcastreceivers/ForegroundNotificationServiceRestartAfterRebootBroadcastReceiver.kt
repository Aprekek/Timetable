package ru.fevgenson.timetable.features.notifications.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.koin.java.KoinJavaComponent.get
import ru.fevgenson.timetable.features.notifications.presentation.ForegroundNotificationService
import ru.fevgenson.timetable.libraries.database.domain.repository.SettingsRepository

class ForegroundNotificationServiceRestartAfterRebootBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, p1: Intent?) {
        val settingsRepository = get(SettingsRepository::class.java)
        if (settingsRepository.getForegroundServiceEnabled()) {
            ForegroundNotificationService.startService(requireNotNull(context))
        } else {
            ForegroundNotificationService.stopService(requireNotNull(context))
        }
    }
}
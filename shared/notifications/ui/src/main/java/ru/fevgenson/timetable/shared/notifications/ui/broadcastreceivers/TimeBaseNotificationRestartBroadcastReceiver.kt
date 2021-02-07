package ru.fevgenson.timetable.shared.notifications.ui.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.GlobalScope
import org.koin.java.KoinJavaComponent.get
import ru.fevgenson.timetable.shared.notifications.ui.schedulers.TimeBaseNotificationScheduler
import ru.fevgenson.timetable.shared.settings.domain.usecase.GetTimeBaseNotificationsEnabledUseCase

class TimeBaseNotificationRestartBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, p1: Intent?) {
        val getTimeBaseNotificationsEnabledUseCase =
            get(GetTimeBaseNotificationsEnabledUseCase::class.java)
        val timeBaseNotificationScheduler = get(TimeBaseNotificationScheduler::class.java)
        if (getTimeBaseNotificationsEnabledUseCase()) {
            timeBaseNotificationScheduler.createTasks(GlobalScope)
        } else {
            timeBaseNotificationScheduler.stopTasks()
        }
    }
}
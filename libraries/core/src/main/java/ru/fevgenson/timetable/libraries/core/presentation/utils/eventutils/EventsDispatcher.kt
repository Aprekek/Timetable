package ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class EventsDispatcher<T> : LifecycleObserver {

    private var mainListener: T? = null
    private var eventListener: T? = null
    private val events = mutableListOf<T.() -> Unit>()

    fun observe(lifecycleOwner: LifecycleOwner, eventListener: T) {
        mainListener = eventListener
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connectEventListener() {
        eventListener = mainListener
        eventListener?.let { listener ->
            events.forEach {
                it(listener)
            }
            events.clear()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun disconnectEventListener() {
        eventListener = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun clear() {
        eventListener = null
        mainListener = null
    }

    fun dispatchEvent(event: T.() -> Unit) {
        eventListener?.let {
            event(it)
        } ?: events.add(event)
    }
}
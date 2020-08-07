package ru.fevgenson.timetable.libraries.core.providers

import android.content.Context

class ResourceProvider(private var context: Context) {

    fun getStringById(id: Int): String = context.getString(id)
}
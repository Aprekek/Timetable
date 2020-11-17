package ru.fevgenson.timetable.libraries.flowbinding

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*


@ExperimentalCoroutinesApi
fun EditText.textToFlow(): Flow<String> = callbackFlow {
    val listener = doAfterTextChanged { offer(it?.toString() ?: "") }
    awaitClose { removeTextChangedListener(listener) }
}

@ExperimentalCoroutinesApi
fun EditText.nullableTextToFlow(): Flow<String?> = callbackFlow {
    val listener = doAfterTextChanged { offer(it?.toString()) }
    awaitClose { removeTextChangedListener(listener) }
}

fun EditText.textBind(flow: Flow<String>, coroutineScope: CoroutineScope) {
    flow.filter { text?.toString() ?: "" != it }
        .onEach { setText(it) }
        .launchIn(coroutineScope)
}

fun EditText.nullableTextBind(flow: Flow<String?>, coroutineScope: CoroutineScope) {
    flow.filter { text?.toString() != it }
        .onEach { setText(it) }
        .launchIn(coroutineScope)
}

@ExperimentalCoroutinesApi
fun EditText.textBind(flow: MutableStateFlow<String>, coroutineScope: CoroutineScope) {
    textBind(flow as Flow<String>, coroutineScope)
    textToFlow().filter { it != flow.value }
        .onEach { flow.value = it }
        .launchIn(coroutineScope)
}


@ExperimentalCoroutinesApi
fun EditText.nullableTextBind(flow: MutableStateFlow<String?>, coroutineScope: CoroutineScope) {
    nullableTextBind(flow as Flow<String?>, coroutineScope)
    nullableTextToFlow().filter { it != flow.value }
        .onEach { flow.value = it }
        .launchIn(coroutineScope)
}
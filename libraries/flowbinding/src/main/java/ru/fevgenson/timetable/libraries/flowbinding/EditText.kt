package ru.fevgenson.timetable.libraries.flowbinding

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
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

fun EditText.textBind(flow: Flow<String>, lifecycleOwner: LifecycleOwner) {
    flow.filter { text?.toString() ?: "" != it }
        .onEach { setText(it) }
        .launchIn(lifecycleOwner.lifecycle.coroutineScope)
}

fun EditText.nullableTextBind(flow: Flow<String?>, lifecycleOwner: LifecycleOwner) {
    flow.filter { text?.toString() != it }
        .onEach { setText(it) }
        .launchIn(lifecycleOwner.lifecycle.coroutineScope)
}

@ExperimentalCoroutinesApi
fun EditText.textBind(flow: MutableStateFlow<String>, lifecycleOwner: LifecycleOwner) {
    textBind(flow as Flow<String>, lifecycleOwner)
    textToFlow().filter { it != flow.value }
        .onEach { flow.value = it }
        .launchIn(lifecycleOwner.lifecycle.coroutineScope)
}


@ExperimentalCoroutinesApi
fun EditText.nullableTextBind(flow: MutableStateFlow<String?>, lifecycleOwner: LifecycleOwner) {
    nullableTextBind(flow as Flow<String?>, lifecycleOwner)
    nullableTextToFlow().filter { it != flow.value }
        .onEach { flow.value = it }
        .launchIn(lifecycleOwner.lifecycle.coroutineScope)
}
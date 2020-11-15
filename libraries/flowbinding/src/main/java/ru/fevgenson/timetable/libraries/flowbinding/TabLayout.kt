package ru.fevgenson.timetable.libraries.flowbinding

import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
fun TabLayout.toFlow(): Flow<Int> = callbackFlow {
    val tabSelectedListener = object : TabLayout.OnTabSelectedListener {

        override fun onTabSelected(tab: TabLayout.Tab?) {
            offer(tab?.position)
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabReselected(tab: TabLayout.Tab?) {
            offer(tab?.position)
        }
    }
    addOnTabSelectedListener(tabSelectedListener)

    awaitClose {
        removeOnTabSelectedListener(tabSelectedListener)
    }
}

fun TabLayout.bind(
    flow: Flow<Int>,
    coroutineScope: CoroutineScope
) {
    flow.onEach {
        getTabAt(it)?.select()
    }.launchIn(coroutineScope)
}

@ExperimentalCoroutinesApi
fun TabLayout.bind(
    flow: MutableStateFlow<Int>,
    coroutineScope: CoroutineScope
) {
    bind(flow as Flow<Int>, coroutineScope)
    toFlow()
        .filter { it != flow.value }
        .onEach { flow.value = it }
        .launchIn(coroutineScope)
}
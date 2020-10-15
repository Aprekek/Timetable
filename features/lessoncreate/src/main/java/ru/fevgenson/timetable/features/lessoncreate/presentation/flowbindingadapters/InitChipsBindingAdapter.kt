package ru.fevgenson.timetable.features.lessoncreate.presentation.flowbindingadapters

import androidx.core.view.children
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
private fun ChipGroup.checkedFlow() = callbackFlow {
    children.forEach { view ->
        view.setOnClickListener {
            val offerResult = mutableListOf<Boolean>()
            children.forEach {
                offerResult.add((it as? Chip)?.isChecked ?: false)
            }
            offer(offerResult)
        }
    }

    awaitClose { }
}

@ExperimentalCoroutinesApi
fun ChipGroup.bindChips(flow: MutableStateFlow<List<Boolean>>, lifecycleOwner: LifecycleOwner) {
    checkedFlow().onEach {
        flow.value = it
    }.launchIn(lifecycleOwner.lifecycle.coroutineScope)

    flow.onEach {
        children.forEachIndexed { index, view ->
            (view as? Chip)?.apply {
                isChecked = it[index]
            }
        }
    }.launchIn(lifecycleOwner.lifecycle.coroutineScope)
}
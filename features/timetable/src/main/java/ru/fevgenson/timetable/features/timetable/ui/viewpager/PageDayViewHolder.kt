package ru.fevgenson.timetable.features.timetable.ui.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.fevgenson.timetable.features.timetable.R
import ru.fevgenson.timetable.features.timetable.databinding.PageDayBinding
import ru.fevgenson.timetable.features.timetable.presentation.PageDayViewModelDelegate
import ru.fevgenson.timetable.features.timetable.ui.bindingadapters.observeUIState
import ru.fevgenson.timetable.features.timetable.ui.recyclerview.LessonListAdapter
import ru.fevgenson.timetable.features.timetable.ui.recyclerview.LessonRecyclerViewItemDecoration
import ru.fevgenson.timetable.features.timetable.ui.recyclerview.LessonViewHolderPool

@ExperimentalCoroutinesApi
class PageDayViewHolder(
    private val viewModelDelegate: PageDayViewModelDelegate,
    private val binding: PageDayBinding,
    private val coroutineScope: CoroutineScope,
    private val lessonViewHolderPool: LessonViewHolderPool
) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun from(
            parent: ViewGroup,
            coroutineScope: CoroutineScope,
            lessonViewHolderPool: LessonViewHolderPool,
            viewModelDelegate: PageDayViewModelDelegate
        ): PageDayViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PageDayBinding.inflate(inflater, parent, false)
            return PageDayViewHolder(
                viewModelDelegate = viewModelDelegate,
                binding = binding,
                coroutineScope = coroutineScope,
                lessonViewHolderPool = lessonViewHolderPool
            )
        }
    }

    init {
        observeUiStates()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            val lessonListAdapter = LessonListAdapter(viewModelDelegate, coroutineScope)
            val dp20 = binding.root.context.resources.getDimensionPixelSize(R.dimen.margin_20)
            addItemDecoration(
                LessonRecyclerViewItemDecoration(
                    verticalSpacePx = dp20,
                    horizontalSpacePx = dp20
                )
            )
            setRecycledViewPool(lessonViewHolderPool)
            layoutManager = LinearLayoutManager(binding.root.context).apply {
                recycleChildrenOnDetach = true
            }
            swapAdapter(lessonListAdapter, true)
            itemAnimator = null
            viewModelDelegate.lessons.onEach {
                lessonListAdapter.submitList(it)
            }.launchIn(coroutineScope)
        }
    }

    private fun observeUiStates() {
        with(binding) {
            recyclerView.observeUIState(viewModelDelegate.uiState, coroutineScope)
            progressBar.observeUIState(viewModelDelegate.uiState, coroutineScope)
            emptyTextView.observeUIState(viewModelDelegate.uiState, coroutineScope)
        }
    }
}
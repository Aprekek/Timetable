package ru.fevgenson.timetable.features.timetable.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import ru.fevgenson.timetable.features.timetable.R
import ru.fevgenson.timetable.features.timetable.databinding.PageDayBinding
import ru.fevgenson.timetable.features.timetable.domain.entities.Lesson
import ru.fevgenson.timetable.features.timetable.presentation.recyclerview.LessonListAdapter
import ru.fevgenson.timetable.features.timetable.presentation.recyclerview.LessonRecyclerViewItemDecoration
import ru.fevgenson.timetable.features.timetable.presentation.recyclerview.LessonViewHolderPool

class PageDayViewHolder(
    private val binding: PageDayBinding,
    private val viewModel: PageDayViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val lessonViewHolderPool: LessonViewHolderPool
) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun from(
            parent: ViewGroup,
            lifecycleOwner: LifecycleOwner,
            viewModel: PageDayViewModel,
            lessonViewHolderPool: LessonViewHolderPool
        ): PageDayViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PageDayBinding.inflate(inflater, parent, false)
            binding.viewModel = viewModel
            binding.lifecycleOwner = lifecycleOwner
            return PageDayViewHolder(
                binding = binding,
                viewModel = viewModel,
                lifecycleOwner = lifecycleOwner,
                lessonViewHolderPool = lessonViewHolderPool
            )
        }
    }

    init {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        initSingleRecyclerView(
            recyclerView = binding.firstWeekRecyclerView,
            adapter = LessonListAdapter(viewModel),
            liveData = viewModel.lessons
        )
    }

    private fun initSingleRecyclerView(
        recyclerView: RecyclerView,
        adapter: LessonListAdapter,
        liveData: LiveData<List<Lesson>>
    ) {
        val dp20 = binding.root.context.resources.getDimensionPixelSize(R.dimen.margin_20)
        recyclerView.addItemDecoration(
            LessonRecyclerViewItemDecoration(
                verticalSpacePx = dp20,
                horizontalSpacePx = dp20
            )
        )
        recyclerView.setRecycledViewPool(lessonViewHolderPool)
        recyclerView.layoutManager = LinearLayoutManager(binding.root.context).apply {
            recycleChildrenOnDetach = true
        }
        recyclerView.swapAdapter(adapter, true)
        (recyclerView.itemAnimator as? SimpleItemAnimator)?.apply {
            supportsChangeAnimations = false
        }
        liveData.observe(lifecycleOwner) {
            adapter.submitList(it)
        }
    }
}
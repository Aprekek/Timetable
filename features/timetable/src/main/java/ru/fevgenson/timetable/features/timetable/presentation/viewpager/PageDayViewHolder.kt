package ru.fevgenson.timetable.features.timetable.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
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
    private val lifecycleOwner: LifecycleOwner,
    private val lessonViewHolderPool: LessonViewHolderPool
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var viewModel: PageDayViewModel
    private lateinit var lessonListAdapter: LessonListAdapter
    private var recyclerViewWasInit = false
    private val listChangeObserver = Observer<List<Lesson>> {
        lessonListAdapter.submitList(it)
    }

    companion object {

        fun from(
            parent: ViewGroup,
            lifecycleOwner: LifecycleOwner,
            lessonViewHolderPool: LessonViewHolderPool
        ): PageDayViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PageDayBinding.inflate(inflater, parent, false)
            binding.lifecycleOwner = lifecycleOwner
            return PageDayViewHolder(
                binding = binding,
                lifecycleOwner = lifecycleOwner,
                lessonViewHolderPool = lessonViewHolderPool
            )
        }
    }

    fun onBind(viewModel: PageDayViewModel) {
        if (recyclerViewWasInit) {
            this.viewModel.lessons.removeObserver(listChangeObserver)
            lessonListAdapter = LessonListAdapter(viewModel)
            binding.firstWeekRecyclerView.swapAdapter(lessonListAdapter, true)
            viewModel.lessons.observe(lifecycleOwner, listChangeObserver)
        }
        this.viewModel = viewModel
        binding.viewModel = viewModel
        if (!recyclerViewWasInit) {
            initRecyclerView()
            recyclerViewWasInit = true
        }
    }

    private fun initRecyclerView() {
        with(binding.firstWeekRecyclerView) {
            lessonListAdapter = LessonListAdapter(viewModel)
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
            (itemAnimator as? SimpleItemAnimator)?.apply {
                supportsChangeAnimations = false
            }
            viewModel.lessons.observe(lifecycleOwner, listChangeObserver)
        }
    }
}
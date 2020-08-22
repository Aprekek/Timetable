package ru.fevgenson.timetable.features.timetable.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import ru.fevgenson.timetable.features.timetable.R
import ru.fevgenson.timetable.features.timetable.databinding.PageDayBinding
import ru.fevgenson.timetable.features.timetable.domain.entities.Lesson
import ru.fevgenson.timetable.features.timetable.presentation.recyclerview.LessonListAdapter
import ru.fevgenson.timetable.features.timetable.presentation.recyclerview.LessonRecyclerViewItemDecoration

class PageDayViewHolder(
    private val binding: PageDayBinding,
    private val viewModel: PageDayViewModel,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun from(
            parent: ViewGroup,
            lifecycleOwner: LifecycleOwner,
            viewModel: PageDayViewModel
        ): PageDayViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PageDayBinding.inflate(inflater, parent, false)
            binding.viewModel = viewModel
            binding.lifecycleOwner = lifecycleOwner
            return PageDayViewHolder(binding, viewModel, lifecycleOwner)
        }
    }

    init {
        viewModel.bind()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        initSingleRecyclerView(
            recyclerView = binding.firstWeekRecyclerView,
            adapter = LessonListAdapter(),
            liveData = viewModel.firstWeekLessons
        )
        initSingleRecyclerView(
            recyclerView = binding.secondWeekRecyclerView,
            adapter = LessonListAdapter(),
            liveData = viewModel.secondWeekLessons
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
        recyclerView.adapter = adapter
        liveData.observe(lifecycleOwner) {
            adapter.submitList(it)
        }
    }
}
package ru.fevgenson.timetable.features.timetable.presentation.viewpager

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils

class DayViewPagerAdapter(
    private val viewModels: List<PageDayViewModel>,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<PageDayViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PageDayViewHolder = PageDayViewHolder.from(parent, lifecycleOwner, viewModels[viewType])

    override fun getItemCount(): Int = DateUtils.WEEK_DAYS

    override fun onBindViewHolder(holder: PageDayViewHolder, position: Int) {}

    override fun getItemViewType(position: Int): Int = position
}
package ru.fevgenson.timetable.features.timetable.presentation.viewpager

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import org.koin.core.KoinComponent
import ru.fevgenson.timetable.features.timetable.presentation.recyclerview.LessonViewHolderPool
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils

class PageDayViewPagerAdapter(
    private val viewModels: List<PageDayViewModel>,
    private val lifecycleOwner: LifecycleOwner,
    private val lessonViewHolderPool: LessonViewHolderPool
) : RecyclerView.Adapter<PageDayViewHolder>(), KoinComponent {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PageDayViewHolder = PageDayViewHolder.from(
        parent = parent,
        lifecycleOwner = lifecycleOwner,
        lessonViewHolderPool = lessonViewHolderPool
    )

    override fun getItemCount(): Int = DateUtils.WEEK_DAYS

    override fun onBindViewHolder(holder: PageDayViewHolder, position: Int) {
        holder.onBind(viewModels[position])
    }

    override fun getItemId(position: Int): Long = position.toLong()
}
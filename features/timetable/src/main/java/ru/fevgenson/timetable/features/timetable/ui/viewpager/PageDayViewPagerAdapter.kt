package ru.fevgenson.timetable.features.timetable.ui.viewpager

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.fevgenson.timetable.features.timetable.presentation.PageDayViewModelDelegate
import ru.fevgenson.timetable.features.timetable.ui.recyclerview.LessonViewHolderPool
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils

@ExperimentalCoroutinesApi
class PageDayViewPagerAdapter(
    private val viewModelDelegates: List<PageDayViewModelDelegate>,
    private val coroutineScope: CoroutineScope,
    private val lessonViewHolderPool: LessonViewHolderPool
) : RecyclerView.Adapter<PageDayViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PageDayViewHolder = PageDayViewHolder.from(
        parent = parent,
        coroutineScope = coroutineScope,
        lessonViewHolderPool = lessonViewHolderPool,
        viewModelDelegate = viewModelDelegates[viewType]
    )

    override fun getItemCount(): Int = DateUtils.WEEK_DAYS

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: PageDayViewHolder, position: Int) {}
}
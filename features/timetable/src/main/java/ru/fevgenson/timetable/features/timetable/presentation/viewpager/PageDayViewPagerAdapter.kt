package ru.fevgenson.timetable.features.timetable.presentation.viewpager

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.parameter.parametersOf
import ru.fevgenson.timetable.features.timetable.presentation.recyclerview.LessonViewHolderPool
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils

class PageDayViewPagerAdapter(
    private val viewModels: List<PageDayViewModel>,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<PageDayViewHolder>(), KoinComponent {

    private var lessonViewHolderPool: LessonViewHolderPool? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PageDayViewHolder = PageDayViewHolder.from(
        parent = parent,
        lifecycleOwner = lifecycleOwner,
        viewModel = viewModels[viewType],
        lessonViewHolderPool = lessonViewHolderPool ?: get<LessonViewHolderPool> {
            parametersOf(parent)
        }.also { lessonViewHolderPool = it }
    )

    override fun getItemCount(): Int = DateUtils.WEEK_DAYS

    override fun onBindViewHolder(holder: PageDayViewHolder, position: Int) {}

    override fun getItemViewType(position: Int): Int = position
}
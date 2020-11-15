package ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.fevgenson.timetable.features.lessoncreate.presentation.LessonCreateViewModel

@ExperimentalCoroutinesApi
class LessonCreateVPAdapter(
    private val viewModel: LessonCreateViewModel,
    private val coroutineScope: CoroutineScope
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            LessonCreateViewModel.MAIN_PAGE -> VHMainPage.from(
                parent = parent,
                viewModel = viewModel,
                coroutineScope = coroutineScope
            )
            LessonCreateViewModel.LOCATION_AND_TYPE_PAGE -> VHLocalAndTypePage.from(
                parent = parent,
                viewModel = viewModel,
                coroutineScope = coroutineScope
            )
            LessonCreateViewModel.TEACHER_PAGE -> VHTeacherPage.from(
                parent = parent,
                viewModel = viewModel,
                coroutineScope = coroutineScope
            )
            else -> throw ClassCastException("Unknown ViewHolder type $viewType")
        }

    override fun getItemCount() = LessonCreateViewModel.PAGES_COUNT

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }
}
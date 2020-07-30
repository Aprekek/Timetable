package ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.fevgenson.timetable.features.lessoncreate.presentation.LessonCreateViewModel

class LessonCreateVPAdapter(private val viewModel: LessonCreateViewModel) :
    RecyclerView.Adapter<ViewHolder>() {

    private companion object {
        const val MAIN_PAGE = 0
        const val LOCATION_AND_TYPE_PAGE = 1
        const val TEACHER_PAGE = 2

        const val PAGES_COUNT = 3
    }

    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            MAIN_PAGE -> VHMainPage.from(parent, viewModel)
            LOCATION_AND_TYPE_PAGE -> VHLocalAndTypePage.from(parent, viewModel)
            TEACHER_PAGE -> VHTeacherPage.from(parent, viewModel)
            else -> throw ClassCastException("Unknown ViewHolder type $viewType")
        }

    override fun getItemCount() = PAGES_COUNT

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(viewModel)
//        holder.binding.executePendingBindings()
    }
}
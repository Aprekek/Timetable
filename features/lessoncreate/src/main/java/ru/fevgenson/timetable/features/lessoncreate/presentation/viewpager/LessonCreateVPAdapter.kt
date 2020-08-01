package ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.fevgenson.timetable.features.lessoncreate.presentation.LessonCreateViewModel
import ru.fevgenson.timetable.features.lessoncreate.utills.LessonCreatePages

class LessonCreateVPAdapter(private val viewModel: LessonCreateViewModel) :
    RecyclerView.Adapter<ViewHolder>() {

    private companion object {

        const val PAGES_COUNT = 3
    }

    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            LessonCreatePages.MAIN_PAGE -> VHMainPage.from(parent, viewModel)
            LessonCreatePages.LOCATION_AND_TYPE_PAGE -> VHLocalAndTypePage.from(parent, viewModel)
            LessonCreatePages.TEACHER_PAGE -> VHTeacherPage.from(parent, viewModel)
            else -> throw ClassCastException("Unknown ViewHolder type $viewType")
        }

    override fun getItemCount() = PAGES_COUNT

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }
}
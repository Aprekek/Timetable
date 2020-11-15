package ru.fevgenson.timetable.features.timetable.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.fevgenson.timetable.features.timetable.databinding.ItemLessonBinding
import ru.fevgenson.timetable.features.timetable.domain.entities.TimetableLesson
import ru.fevgenson.timetable.features.timetable.presentation.PageDayViewModelDelegate
import ru.fevgenson.timetable.libraries.core.presentation.bindingadapters.*

@ExperimentalCoroutinesApi
class LessonViewHolder private constructor(
    private val binding: ItemLessonBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        const val VIEW_HOLDER_TYPE = -1

        fun from(
            parent: ViewGroup
        ): LessonViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemLessonBinding.inflate(inflater, parent, false)
            return LessonViewHolder(binding)
        }
    }

    fun bind(
        timetableLesson: TimetableLesson,
        viewModelDelegate: PageDayViewModelDelegate,
        coroutineScope: CoroutineScope
    ) {
        with(binding) {
            subject.text = timetableLesson.subject
            time.text = timetableLesson.time
            typeAndTeacher.setTypeAndTeacher(
                type = timetableLesson.type,
                teacher = timetableLesson.teacher
            )
            housingAndClassroom.setHousingAndClassroom(
                housing = timetableLesson.housing,
                classroom = timetableLesson.classroom
            )
            now.initCurrentLessonObserver(
                lessonWeek = timetableLesson.weekType,
                lessonDay = timetableLesson.day,
                timeDiapason = timetableLesson.time,
                coroutineScope = coroutineScope
            )
            dynamicTime.initTimeObserver(
                lessonWeek = timetableLesson.weekType,
                lessonDay = timetableLesson.day,
                timeDiapason = timetableLesson.time,
                coroutineScope = coroutineScope
            )
            cardView.initPopUpMenu(
                editMenuListener = { viewModelDelegate.editLesson(timetableLesson.id) },
                copyMenuListener = { viewModelDelegate.copyLesson(timetableLesson.id) },
                deleteMenuListener = { viewModelDelegate.deleteLesson(timetableLesson.id) }
            )
        }
    }
}
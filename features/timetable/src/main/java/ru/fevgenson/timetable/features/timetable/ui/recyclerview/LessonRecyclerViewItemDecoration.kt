package ru.fevgenson.timetable.features.timetable.ui.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class LessonRecyclerViewItemDecoration(
    private val verticalSpacePx: Int,
    private val horizontalSpacePx: Int
) : RecyclerView.ItemDecoration() {

    private companion object {
        const val WITHOUT_SPACE = 0
        const val FIRST_ITEM = 0
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = horizontalSpacePx
        outRect.right = horizontalSpacePx
        outRect.bottom = verticalSpacePx
        outRect.top = when (parent.getChildLayoutPosition(view)) {
            FIRST_ITEM -> verticalSpacePx
            else -> WITHOUT_SPACE
        }
    }
}
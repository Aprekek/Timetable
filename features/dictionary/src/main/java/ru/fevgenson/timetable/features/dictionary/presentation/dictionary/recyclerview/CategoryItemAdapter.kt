package ru.fevgenson.timetable.features.dictionary.presentation.dictionary.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.fevgenson.timetable.features.dictionary.presentation.dictionary.viewpager.PageCategoryViewModel
import ru.fevgenson.timetable.shared.lesson.domain.entity.SubcategoryEntity

class CategoryItemAdapter(
    private val pageCategoryViewModel: PageCategoryViewModel
) : ListAdapter<SubcategoryEntity, CategoryItemViewHolder>(CategoryItemDiffUtils()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryItemViewHolder = CategoryItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.bind(getItem(position), pageCategoryViewModel)
    }
}

private class CategoryItemDiffUtils : DiffUtil.ItemCallback<SubcategoryEntity>() {

    override fun areItemsTheSame(
        oldItem: SubcategoryEntity,
        newItem: SubcategoryEntity
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: SubcategoryEntity,
        newItem: SubcategoryEntity
    ): Boolean = oldItem == newItem
}
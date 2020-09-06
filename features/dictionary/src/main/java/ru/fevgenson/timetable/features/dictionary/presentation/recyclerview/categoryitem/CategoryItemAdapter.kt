package ru.fevgenson.timetable.features.dictionary.presentation.recyclerview.categoryitem

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.fevgenson.timetable.features.dictionary.presentation.viewpager.PageCategoryViewModel

class CategoryItemAdapter(
    private val pageCategoryViewModel: PageCategoryViewModel
) : ListAdapter<String, CategoryItemViewHolder>(CategoryItemDiffUtils()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryItemViewHolder = CategoryItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.bind(getItem(position), pageCategoryViewModel)
    }

}

private class CategoryItemDiffUtils : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
}
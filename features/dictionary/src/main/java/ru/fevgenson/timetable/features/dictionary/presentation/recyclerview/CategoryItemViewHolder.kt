package ru.fevgenson.timetable.features.dictionary.presentation.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.fevgenson.timetable.features.dictionary.databinding.CategoryItemBinding
import ru.fevgenson.timetable.features.dictionary.presentation.viewpager.PageCategoryViewModel

class CategoryItemViewHolder(
    private val binding: CategoryItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): CategoryItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CategoryItemBinding.inflate(layoutInflater, parent, false)
            return CategoryItemViewHolder(binding)
        }
    }

    fun bind(categoryItemName: String, pageCategoryViewModel: PageCategoryViewModel) {
        binding.text = categoryItemName
        binding.pageCategoryViewModel = pageCategoryViewModel
    }
}
package ru.fevgenson.timetable.features.dictionary.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import ru.fevgenson.timetable.features.dictionary.databinding.PageCategoryBinding
import ru.fevgenson.timetable.features.dictionary.presentation.recyclerview.CategoryItemAdapter

class PageCategoryViewHolder private constructor(
    private val binding: PageCategoryBinding
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var pageCategoryViewModel: PageCategoryViewModel
    private lateinit var categoryItemAdapter: CategoryItemAdapter

    companion object {

        fun from(
            parent: ViewGroup,
            lifecycleOwner: LifecycleOwner
        ): PageCategoryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PageCategoryBinding.inflate(inflater, parent, false)
            binding.lifecycleOwner = lifecycleOwner
            return PageCategoryViewHolder(binding)
        }
    }

    fun bind(pageCategoryViewModel: PageCategoryViewModel) {
        this.pageCategoryViewModel = pageCategoryViewModel
    }
}
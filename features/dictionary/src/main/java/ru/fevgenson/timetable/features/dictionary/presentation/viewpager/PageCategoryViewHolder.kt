package ru.fevgenson.timetable.features.dictionary.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import ru.fevgenson.timetable.features.dictionary.databinding.PageCategoryBinding
import ru.fevgenson.timetable.features.dictionary.presentation.recyclerview.CategoryItemAdapter

class PageCategoryViewHolder private constructor(
    private val binding: PageCategoryBinding,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var pageCategoryViewModel: PageCategoryViewModel
    private lateinit var categoryItemAdapter: CategoryItemAdapter
    private val observer = Observer<List<String>> {
        categoryItemAdapter.submitList(it)
    }

    companion object {

        fun from(
            parent: ViewGroup,
            lifecycleOwner: LifecycleOwner
        ): PageCategoryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PageCategoryBinding.inflate(inflater, parent, false)
            binding.lifecycleOwner = lifecycleOwner
            return PageCategoryViewHolder(binding, lifecycleOwner)
        }
    }

    fun bind(pageCategoryViewModel: PageCategoryViewModel) {
        this.pageCategoryViewModel = pageCategoryViewModel
    }
}
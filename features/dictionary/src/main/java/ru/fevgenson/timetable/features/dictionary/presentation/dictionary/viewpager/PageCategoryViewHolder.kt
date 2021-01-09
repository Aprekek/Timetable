package ru.fevgenson.timetable.features.dictionary.presentation.dictionary.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import ru.fevgenson.timetable.features.dictionary.databinding.PageCategoryBinding
import ru.fevgenson.timetable.features.dictionary.presentation.dictionary.recyclerview.CategoryItemAdapter
import ru.fevgenson.timetable.shared.lesson.domain.entity.SubcategoryEntity

class PageCategoryViewHolder private constructor(
    private val binding: PageCategoryBinding,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var pageCategoryViewModel: PageCategoryViewModel
    private lateinit var categoryItemAdapter: CategoryItemAdapter
    private var isAdapterInit: Boolean = false
    private val onListChangeObserver = Observer<List<SubcategoryEntity>> {
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
        binding.viewModel = pageCategoryViewModel

        if (!isAdapterInit) {
            initAdapter()
            isAdapterInit = true
        }
    }

    private fun initAdapter() {
        categoryItemAdapter = CategoryItemAdapter(pageCategoryViewModel)
        binding.categoryItemsRecyclerView.swapAdapter(categoryItemAdapter, true)
        pageCategoryViewModel.listCategoryItemsLiveData.observe(
            lifecycleOwner,
            onListChangeObserver
        )
    }
}
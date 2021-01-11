package ru.fevgenson.timetable.features.dictionary.presentation.dictionary.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.fevgenson.timetable.features.dictionary.databinding.PageCategoryBinding
import ru.fevgenson.timetable.features.dictionary.presentation.dictionary.recyclerview.CategoryItemAdapter
import ru.fevgenson.timetable.libraries.flowbinding.bindTextResOrGone

class PageCategoryViewHolder private constructor(
    private val binding: PageCategoryBinding,
    private val scope: CoroutineScope
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var pageCategoryDelegate: PageCategoryDelegate
    private lateinit var categoryItemAdapter: CategoryItemAdapter
    private var isAdapterInit: Boolean = false

    companion object {

        fun from(
            parent: ViewGroup,
            scope: CoroutineScope
        ): PageCategoryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PageCategoryBinding.inflate(inflater, parent, false)
            return PageCategoryViewHolder(binding, scope)
        }
    }

    fun bind(pageCategoryDelegate: PageCategoryDelegate) {
        this.pageCategoryDelegate = pageCategoryDelegate
        binding.isNoItemsTextView.bindTextResOrGone(
            pageCategoryDelegate.isNoItemsTextVisible,
            scope
        )

        if (!isAdapterInit) {
            initAdapter()
            isAdapterInit = true
        }
    }

    private fun initAdapter() {
        categoryItemAdapter = CategoryItemAdapter(pageCategoryDelegate)
        binding.categoryItemsRecyclerView.swapAdapter(categoryItemAdapter, true)
        pageCategoryDelegate.categoryItemsList.onEach {
            categoryItemAdapter.submitList(it)
        }.launchIn(scope)
    }
}
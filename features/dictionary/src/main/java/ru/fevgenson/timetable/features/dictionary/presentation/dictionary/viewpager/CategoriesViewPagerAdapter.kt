package ru.fevgenson.timetable.features.dictionary.presentation.dictionary.viewpager

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import ru.fevgenson.timetable.features.dictionary.domain.Categories
import ru.fevgenson.timetable.features.dictionary.presentation.dictionary.DictionaryViewModel

class CategoriesViewPagerAdapter(
    private val parentViewModel: DictionaryViewModel,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<PageCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageCategoryViewHolder =
        PageCategoryViewHolder.from(parent, lifecycleOwner)

    override fun onBindViewHolder(holder: PageCategoryViewHolder, position: Int) {
        holder.bind(parentViewModel.listOfPageCategoryViewModel[position])
    }

    override fun getItemCount() = Categories.TOTAL_CATEGORIES
}
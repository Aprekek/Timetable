package ru.fevgenson.timetable.features.dictionary.presentation.dictionary.viewpager

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import ru.fevgenson.timetable.features.dictionary.domain.Categories
import ru.fevgenson.timetable.features.dictionary.presentation.dictionary.DictionaryViewModel

class CategoriesViewPagerAdapter(
    private val parentViewModel: DictionaryViewModel,
    private val scope: CoroutineScope
) : RecyclerView.Adapter<PageCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageCategoryViewHolder =
        PageCategoryViewHolder.from(parent, scope)

    override fun onBindViewHolder(holder: PageCategoryViewHolder, position: Int) {
        holder.bind(parentViewModel.listOfPageCategoryViewModel[position])
    }

    override fun getItemCount() = Categories.TOTAL_CATEGORIES
}
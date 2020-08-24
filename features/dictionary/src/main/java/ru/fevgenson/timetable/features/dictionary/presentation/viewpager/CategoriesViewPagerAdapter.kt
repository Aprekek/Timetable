package ru.fevgenson.timetable.features.dictionary.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.fevgenson.timetable.features.dictionary.databinding.PageCategoryBinding

class CategoriesViewPagerAdapter(
    private val categoriesNames: Array<String>
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categoriesNames[position])
    }

    override fun getItemCount() = categoriesNames.size
}

class ViewHolder private constructor(private var binding: PageCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PageCategoryBinding.inflate(inflater, parent, false)
            return ViewHolder(binding = binding)
        }
    }

    fun bind(categoryName: String) {
        binding.categoryTextView.text = categoryName
    }
}
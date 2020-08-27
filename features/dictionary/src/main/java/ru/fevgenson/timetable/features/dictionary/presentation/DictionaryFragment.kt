package ru.fevgenson.timetable.features.dictionary.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ru.fevgenson.timetable.features.dictionary.R
import ru.fevgenson.timetable.features.dictionary.databinding.FragmentDictionaryBinding
import ru.fevgenson.timetable.features.dictionary.databinding.TabCategoryBinding
import ru.fevgenson.timetable.features.dictionary.presentation.viewpager.CategoriesViewPagerAdapter

class DictionaryFragment : Fragment() {

    private lateinit var binding: FragmentDictionaryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        initViewPager2()
        initTabLayoutMediator()
        return binding.root
    }

    private fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dictionary, container, false)
    }

    private fun initViewPager2() {
        binding.viewPager.adapter =
            CategoriesViewPagerAdapter(resources.getStringArray(R.array.dictionary_categories))
    }

    private fun initTabLayoutMediator() {
        val tabCategories = resources.getStringArray(R.array.dictionary_categories)

        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab: TabLayout.Tab, i: Int ->
            val tabCategoryBinding = DataBindingUtil.inflate<TabCategoryBinding>(
                LayoutInflater.from(context),
                R.layout.tab_category,
                binding.tabLayout,
                false
            )
            tabCategoryBinding.categoryChip.text = tabCategories[i]
            tabCategoryBinding.categoryChip.setOnClickListener {
                tab.parent?.selectTab(tab)
            }
            tab.customView = tabCategoryBinding.root
            tab.view.isClickable = false
        }.attach()
    }
}
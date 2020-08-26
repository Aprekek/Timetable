package ru.fevgenson.timetable.features.dictionary.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
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
        initLayoutManager()
        initTabLayoutMediator()
        Log.d("B", "3")
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
            CategoriesViewPagerAdapter(resources.getStringArray(R.array.categories))
    }

    private fun initLayoutManager() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.initColors(R.color.blue, R.color.white)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.initColors(R.color.light_grey, R.color.black)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun TabLayout.Tab.initColors(
        backgroundColor: Int,
        textColor: Int
    ) {
        if (customView is Chip) {
            (customView as Chip).apply {
                setTextColor(resources.getColor(textColor))
                chipBackgroundColor = resources.getColorStateList(backgroundColor)
            }
        }
    }

    private fun initTabLayoutMediator() {
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
            tabCategoryBinding.categoryChip.text = resources.getStringArray(R.array.categories)[i]
            tabCategoryBinding.categoryChip.setOnClickListener {
                tab.parent?.selectTab(tab) ?: throw NullPointerException(
                    "DictionaryFragment::initCategoryTabs() - TabLayout must be not null"
                )
            }
            tab.customView = tabCategoryBinding.categoryChip
            tab.view.isClickable = false
        }.attach()
    }
}
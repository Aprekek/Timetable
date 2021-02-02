package ru.fevgenson.timetable.features.dictionary.presentation.dictionary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.fevgenson.libraries.navigation.di.NavigationConstants
import ru.fevgenson.timetable.features.dictionary.R
import ru.fevgenson.timetable.features.dictionary.databinding.FragmentDictionaryBinding
import ru.fevgenson.timetable.features.dictionary.domain.Categories
import ru.fevgenson.timetable.features.dictionary.presentation.dictionary.viewpager.CategoriesViewPagerAdapter

class DictionaryFragment : Fragment(), DictionaryViewModel.EventListener {

    private val dictionaryViewModel: DictionaryViewModel by viewModel()
    private lateinit var binding: FragmentDictionaryBinding
    private lateinit var tabCategories: Array<out String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initBinding(inflater, container)
        initResources()
        initViewPager2()
        initTabLayoutMediator()
        initEventListener()
        return binding.root
    }

    private fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentDictionaryBinding.inflate(inflater, container, false)
    }

    private fun initResources() {
        tabCategories = resources.getStringArray(R.array.dictionary_categories)
    }

    private fun initViewPager2() {
        val adapter = CategoriesViewPagerAdapter(dictionaryViewModel, viewLifecycleOwner.lifecycleScope)
        binding.viewPager.adapter = adapter
        binding.viewPager.offscreenPageLimit = adapter.itemCount
    }

    private fun initTabLayoutMediator() {
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab: TabLayout.Tab, i: Int ->
            tab.text = tabCategories[i]
        }.attach()

        binding.tabLayout.setTabMargins(
            resources.getDimensionPixelSize(R.dimen.margin_padding_very_small),
            resources.getDimensionPixelSize(R.dimen.padding_5)
        )
    }

    private fun initEventListener() {
        dictionaryViewModel.eventsDispatcher.observe(viewLifecycleOwner, this)
    }

    override fun goToListOfLessonsByCategoryFragment(
        categoryType: Categories.CategoryTypes,
        categoryItem: String
    ) {
        val arguments = Bundle().apply {
            with(NavigationConstants.ListLessonsByCategory) {
                putInt(CATEGORY, categoryType.value)
                putString(CATEGORY_NAME, tabCategories[categoryType.value])
                putString(CATEGORY_ITEM_NAME, categoryItem)
            }
        }
        Navigation.findNavController(requireActivity(), R.id.global_host)
            .navigate(R.id.navigation_from_main_to_list_of_lessons_by_category, arguments)
    }

    private fun TabLayout.setTabMargins(
        tabMarginDpHorizontal: Int,
        tabMarginDpVertical: Int
    ) {
        val tabGroup = (getChildAt(0) as ViewGroup)
        for (i in 0 until tabCount) {
            val tab = tabGroup.getChildAt(i)
            val tabParameters = tab.layoutParams as ViewGroup.MarginLayoutParams
            tabParameters.setMargins(
                tabMarginDpHorizontal,
                tabMarginDpVertical,
                tabMarginDpHorizontal,
                tabMarginDpVertical
            )
        }
        tabGroup.requestLayout()
    }
}
package ru.fevgenson.timetable.features.dictionary.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.fevgenson.timetable.features.dictionary.R
import ru.fevgenson.timetable.features.dictionary.databinding.FragmentDictionaryBinding
import ru.fevgenson.timetable.features.dictionary.presentation.viewpager.CategoriesViewPagerAdapter

class DictionaryFragment : Fragment(), DictionaryViewModel.EventListener {

    private val dictionaryViewModel: DictionaryViewModel by viewModel()
    private lateinit var binding: FragmentDictionaryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        initViewPager2()
        initTabLayoutMediator()
        initEventListener()
        return binding.root
    }

    private fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dictionary, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initViewPager2() {
        binding.viewPager.adapter =
            CategoriesViewPagerAdapter(dictionaryViewModel, viewLifecycleOwner)
    }

    private fun initTabLayoutMediator() {
        val tabCategories = resources.getStringArray(R.array.dictionary_categories)

        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab: TabLayout.Tab, i: Int ->
            tab.text = tabCategories[i]
        }.attach()
    }

    private fun initEventListener() {
        dictionaryViewModel.eventsDispatcher.observe(viewLifecycleOwner, this)
    }

    override fun goToListOfLessonsByCategoryFragment(categoryItem: String) {
        Toast.makeText(context, categoryItem, Toast.LENGTH_SHORT).show()
    }
}
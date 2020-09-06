package ru.fevgenson.timetable.features.dictionary.presentation.lessonsbycategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.fevgenson.libraries.navigation.di.NavigationConstants
import ru.fevgenson.timetable.features.dictionary.R
import ru.fevgenson.timetable.features.dictionary.databinding.FragmentListOfLessonsByCategoryBinding
import ru.fevgenson.timetable.features.dictionary.presentation.lessonsbycategory.recyclerview.DictionaryLessonListAdapter

class ListOfLessonsByCategoryFragment : Fragment() {

    private lateinit var binding: FragmentListOfLessonsByCategoryBinding
    private lateinit var adapter: DictionaryLessonListAdapter
    private val viewModel: ListOfLessonsByCategoryViewModel by viewModel {
        with(requireArguments()) {
            with(NavigationConstants.ListLessonsByCategory) {
                parametersOf(
                    getString(CATEGORY_ITEM_NAME),
                    getString(CATEGORY_NAME),
                    getInt(CATEGORY)
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        initAdapter()
        initObserver()

        return binding.root
    }

    private fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list_of_lessons_by_category,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun initAdapter() {
        adapter = DictionaryLessonListAdapter(viewModel)
        binding.dictionaryListOfLessonsRecyclerView.adapter = adapter
    }

    private fun initObserver() {
        viewModel.lessons.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }
}
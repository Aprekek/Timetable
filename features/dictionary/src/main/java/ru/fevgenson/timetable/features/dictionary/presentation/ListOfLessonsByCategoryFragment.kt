package ru.fevgenson.timetable.features.dictionary.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.fevgenson.libraries.navigation.di.NavigationConstants
import ru.fevgenson.timetable.features.dictionary.R
import ru.fevgenson.timetable.features.dictionary.databinding.FragmentListOfLessonsByCategoryBinding

class ListOfLessonsByCategoryFragment : Fragment() {

    private lateinit var binding: FragmentListOfLessonsByCategoryBinding
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
}
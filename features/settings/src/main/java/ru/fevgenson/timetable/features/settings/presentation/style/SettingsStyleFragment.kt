package ru.fevgenson.timetable.features.settings.presentation.style

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.fevgenson.timetable.features.settings.R
import ru.fevgenson.timetable.features.settings.databinding.FragmentSettingsStyleBinding

class SettingsStyleFragment : Fragment(), SettingsStyleViewModel.EventListener {

    private lateinit var binding: FragmentSettingsStyleBinding
    private val viewModel by viewModel<SettingsStyleViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        initViewModel()
        return binding.root
    }

    private fun initViewModel() {
        viewModel.eventsDispatcher.observe(viewLifecycleOwner, this)
        viewModel.selectedTheme.observe(viewLifecycleOwner) {
            if (it != AppCompatDelegate.getDefaultNightMode()) {
                AppCompatDelegate.setDefaultNightMode(it)
            }
        }
    }

    private fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_settings_style,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    override fun navigateBack() {
        findNavController().popBackStack()
    }
}
package ru.fevgenson.timetable.features.settings.presentation.backup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.fevgenson.timetable.features.settings.R
import ru.fevgenson.timetable.features.settings.databinding.FragmentSettingsBackupBinding

class SettingsBackupFragment : Fragment(), SettingsBackupViewModel.EventListener {

    private lateinit var binding: FragmentSettingsBackupBinding
    private val viewModel: SettingsBackupViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(layoutInflater, container)
        initViewModel()
        return binding.root
    }

    private fun initViewModel() {
        viewModel.eventsDispatcher.observe(viewLifecycleOwner, this)
    }

    private fun initBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_settings_backup,
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
package ru.fevgenson.timetable.features.settings.presentation.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.fevgenson.timetable.features.notifications.presentation.ForegroundNotificationService
import ru.fevgenson.timetable.features.settings.R
import ru.fevgenson.timetable.features.settings.databinding.FragmentSettingsNotificationsBinding

class SettingsNotificationsFragment : Fragment(), SettingsNotificationsViewModel.EventListener {

    private lateinit var binding: FragmentSettingsNotificationsBinding
    private val viewModel by viewModel<SettingsNotificationsViewModel>()

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
        viewModel.foregroundServiceEnabled.observe(viewLifecycleOwner) { startService ->
            if (startService) {
                ForegroundNotificationService.startService(requireActivity())
            } else {
                ForegroundNotificationService.stopService(requireActivity())
            }
        }
    }

    private fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_settings_notifications,
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
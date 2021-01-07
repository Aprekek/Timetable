package ru.fevgenson.timetable.features.main.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ru.fevgenson.libraries.navigation.di.KeepStateNavigator
import ru.fevgenson.libraries.navigation.di.setupWithKeepStateNavController
import ru.fevgenson.timetable.features.main.R
import ru.fevgenson.timetable.features.main.databinding.FragmentMainBinding
import ru.fevgenson.timetable.libraries.core.presentation.fragment.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private var navigationInit = false
    private lateinit var navController: NavController
    private lateinit var navigator: KeepStateNavigator

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        if (!navigationInit) {
            val navHostFragment = childFragmentManager.findFragmentById(R.id.main_host)!!
            navigator = KeepStateNavigator(
                context = requireContext(),
                manager = navHostFragment.childFragmentManager,
                containerId = R.id.main_host
            )
            navController = Navigation.findNavController(requireActivity(), R.id.main_host)
            navController.navigatorProvider.addNavigator(navigator)
            navController.setGraph(R.navigation.navigation_bottom_main)
            navigationInit = true
        }
        binding.bottomNavigation.setupWithKeepStateNavController(navController, navigator)
    }
}
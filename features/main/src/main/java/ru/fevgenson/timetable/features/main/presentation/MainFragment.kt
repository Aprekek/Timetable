package ru.fevgenson.timetable.features.main.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ru.fevgenson.libraries.navigation.di.KeepStateNavigator
import ru.fevgenson.libraries.navigation.di.setupWithKeepStateNavController
import ru.fevgenson.timetable.features.main.R
import ru.fevgenson.timetable.features.main.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private var navigationInit = false
    private lateinit var navController: NavController
    private lateinit var navigator: KeepStateNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

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
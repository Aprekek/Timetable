package ru.fevgenson.libraries.navigation.di

import android.view.MenuItem
import androidx.core.view.forEach
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.fevgenson.libraries.navigation.R

fun BottomNavigationView.setupWithKeepStateNavController(
    navController: NavController,
    keepStateNavigator: KeepStateNavigator
) {
    setOnNavigationItemSelectedListener { navController.onNavDestinationSelected(it) }
    navController.addOnDestinationChangedListener { _, _, _ ->
        val backStackId = keepStateNavigator.popBackStackId.last()
        menu.forEach {
            if (backStackId == it.itemId) {
                it.isChecked = true
            }
        }
    }
}

private fun NavController.onNavDestinationSelected(item: MenuItem): Boolean {
    val builder = NavOptions.Builder()
        .setEnterAnim(R.anim.nav_default_enter_anim)
        .setExitAnim(R.anim.nav_default_exit_anim)
        .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
        .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
    val options = builder.build()
    return try {
        navigate(item.itemId, null, options)
        true
    } catch (e: IllegalArgumentException) {
        false
    }
}
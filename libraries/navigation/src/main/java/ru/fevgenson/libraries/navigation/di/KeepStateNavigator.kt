package ru.fevgenson.libraries.navigation.di

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import java.util.*

@Navigator.Name("keep_state_fragment")
class KeepStateNavigator(
    private val context: Context,
    private val manager: FragmentManager,
    private val containerId: Int
) : FragmentNavigator(context, manager, containerId) {

    private companion object {
        const val POP_BACK_STACK = "popBackStack"
        const val POP_BACK_STACK_ID = "popBackStackId"
    }

    private val popBackStack = mutableListOf<String>()
    private val _popBackStackId = mutableListOf<Int>()
    val popBackStackId: List<Int>
        get() = _popBackStackId

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination? =
        if (
            navigate(
                destinationClassName = destination.className,
                addToPopBackStack = true,
                destination = destination
            )
        ) destination else null

    @ExperimentalStdlibApi
    override fun popBackStack(): Boolean {
        if (popBackStack.isEmpty()) {
            return false
        }
        val destinationClassName = popBackStack[popBackStack.lastIndex - 1]
        navigate(destinationClassName)
        popBackStack.removeLast()
        _popBackStackId.removeLast()
        return true
    }

    private fun navigate(
        destinationClassName: String,
        addToPopBackStack: Boolean = false,
        destination: NavDestination? = null
    ): Boolean {
        var result = true
        val transaction = manager.beginTransaction()
        val currentFragment = manager.primaryNavigationFragment

        if (currentFragment != null) {
            if (currentFragment.javaClass.name == destinationClassName) {
                return false
            }
            transaction.hide(currentFragment)
        }

        if (addToPopBackStack) {
            result = popBackStack.addToPop(destinationClassName)
            _popBackStackId.add(destination?.id!!)
        }

        var fragment = manager.findFragmentByTag(destinationClassName)
        if (fragment == null) {
            fragment =
                manager.fragmentFactory.instantiate(context.classLoader, destinationClassName)
            transaction.add(containerId, fragment, destinationClassName)
        } else {
            transaction.show(fragment)
        }

        transaction.setPrimaryNavigationFragment(fragment)
        transaction.setReorderingAllowed(true)
        transaction.commitNow()

        return result
    }

    override fun onSaveState(): Bundle? {
        val bundle = Bundle()
        bundle.putStringArrayList(POP_BACK_STACK, popBackStack as ArrayList<String>)
        bundle.putIntegerArrayList(POP_BACK_STACK_ID, popBackStackId as ArrayList<Int>)
        return bundle
    }

    override fun onRestoreState(savedState: Bundle?) {
        savedState?.let {
            savedState.getStringArrayList(POP_BACK_STACK)?.let {
                popBackStack.clear()
                popBackStack.addAll(it)
            }
            savedState.getIntegerArrayList(POP_BACK_STACK_ID)?.let {
                _popBackStackId.clear()
                _popBackStackId.addAll(it)
            }
        }
    }

    private fun MutableList<String>.addToPop(tag: String): Boolean {
        val position = indexOf(tag)
        if (position != -1) {
            removeAt(position)
            _popBackStackId.removeAt(position)
        }
        add(tag)
        return position == -1
    }
}
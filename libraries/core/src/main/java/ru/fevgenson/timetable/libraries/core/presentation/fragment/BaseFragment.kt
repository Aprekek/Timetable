package ru.fevgenson.timetable.libraries.core.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    private var _binding: T? = null
    val binding: T
        get() = _binding ?: throw IllegalStateException("Binding не может быть null")

    val coroutineScope
        get() = viewLifecycleOwner.lifecycle.coroutineScope

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getBinding(inflater, container)
        return binding.root
    }

    abstract fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): T
}
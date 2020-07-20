package ru.fevgenson.timetable.features.timetable.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.fevgenson.timetable.features.timetable.R
import ru.fevgenson.timetable.features.timetable.databinding.FragmentTimetableBinding

class TimetableFragment : Fragment() {

    private lateinit var binding: FragmentTimetableBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timetable, container, false)
        return binding.root
    }
}
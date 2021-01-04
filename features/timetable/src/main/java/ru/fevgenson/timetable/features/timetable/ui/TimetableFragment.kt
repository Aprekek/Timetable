package ru.fevgenson.timetable.features.timetable.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.scope.newScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.fevgenson.timetable.features.timetable.R
import ru.fevgenson.timetable.features.timetable.databinding.FragmentTimetableBinding
import ru.fevgenson.timetable.features.timetable.presentation.TimetableEvent
import ru.fevgenson.timetable.features.timetable.presentation.TimetableViewModel
import ru.fevgenson.timetable.features.timetable.ui.bindingadapters.setCurrentDayIcon
import ru.fevgenson.timetable.features.timetable.ui.bindingadapters.setCurrentWeekIcon
import ru.fevgenson.timetable.features.timetable.ui.bindingadapters.setDates
import ru.fevgenson.timetable.features.timetable.ui.bindingadapters.setTabsColors
import ru.fevgenson.timetable.features.timetable.ui.tabs.binding
import ru.fevgenson.timetable.features.timetable.ui.tabs.createTab
import ru.fevgenson.timetable.features.timetable.ui.tabs.createTabFrom
import ru.fevgenson.timetable.features.timetable.ui.viewpager.PageDayViewPagerAdapter
import ru.fevgenson.timetable.libraries.core.presentation.colors.ColorUtils
import ru.fevgenson.timetable.libraries.core.presentation.dialogs.NoticeDialogFragment
import ru.fevgenson.timetable.libraries.core.presentation.fragment.BaseFragment
import ru.fevgenson.timetable.libraries.flowbinding.bind
import ru.fevgenson.timetable.shared.timeutils.domain.constants.WeekTypes

@ExperimentalCoroutinesApi
class TimetableFragment : BaseFragment<FragmentTimetableBinding>(),
    NoticeDialogFragment.NoticeDialogListener<Long> {

    private val viewModel: TimetableViewModel by viewModel()
    private val koinScope = newScope(this)

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTimetableBinding = FragmentTimetableBinding.inflate(inflater, container, false)

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        initViewPagerAdapter()
        initTabs()
        initEventListener()
        observeViewModel()
    }

    private fun initViewPagerAdapter() {
        val adapter = PageDayViewPagerAdapter(
            viewModel.dayViewModelsList,
            coroutineScope,
            koinScope.get()
        )
        with(binding.dayViewPager) {
            this.adapter = adapter
            setCurrentItem(viewModel.selectedDay.value, false)
            offscreenPageLimit = 7
        }
    }

    private fun initTabs() {
        addWeekTabs()
        addDayTabs()
        binding.weekTabLayout.initTabsColor()
        binding.dayTabLayout.initTabsColor()
    }

    private fun addWeekTabs() {
        val titles = resources.getStringArray(R.array.timetable_week_tabs)
        for (position in 0 until WeekTypes.WEEK_TYPES_COUNT) {
            val tab = binding.weekTabLayout.createTab()
            tab.binding.nameTextView.text = titles[position]
            tab.binding.dateTextView.isVisible = false
            binding.weekTabLayout.addTab(tab)
        }
    }

    private fun addDayTabs() {
        val titles = resources.getStringArray(R.array.timetable_day_tabs)
        TabLayoutMediator(
            binding.dayTabLayout,
            binding.dayViewPager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.createTabFrom(binding.dayTabLayout)
            tab.binding.nameTextView.text = titles[position]
        }.attach()
    }

    private fun TabLayout.initTabsColor() {
        setTabsColors(
            selectedColor = ColorUtils.getColorFromAttribute(
                R.attr.colorPrimary,
                requireContext()
            ),
            normalColor = ColorUtils.getColorFromAttribute(
                R.attr.colorControlNormal,
                requireContext()
            )
        )
    }

    private fun observeViewModel() {
        with(binding) {
            addButton.setOnClickListener { viewModel.onCreateLessonButtonClick() }
            with(weekTabLayout) {
                setCurrentWeekIcon(viewModel.currentWeekType, coroutineScope)
                bind(viewModel.selectedWeekType, coroutineScope)
            }
            with(dayTabLayout) {
                setDates(viewModel.selectedWeekTypeDates, coroutineScope)
                setCurrentDayIcon(
                    isCurrentWeekFlow = viewModel.selectedWeekTypeIsCurrentWeekType,
                    currentDayFlow = viewModel.currentDay,
                    coroutineScope = coroutineScope
                )
                bind(viewModel.selectedDay, coroutineScope)
            }
        }
    }

    private fun initEventListener() {
        viewModel.eventLiveData.observe(viewLifecycleOwner) { event ->
            when (event) {
                is TimetableEvent.ShowDeleteDialogEvent -> showDeleteDialog(event.lessonId)
                is TimetableEvent.NavigateToCreateEvent -> navigateToCreate(event.bundle)
            }
        }
    }

    private fun navigateToCreate(bundle: Bundle) {
        Navigation.findNavController(
            requireActivity(),
            R.id.global_host
        ).navigate(
            R.id.navigation_from_main_to_lesson_create,
            bundle
        )
    }

    private fun showDeleteDialog(lessonId: Long) {
        val dialog = NoticeDialogFragment.newInstance(
            title = R.string.timetable_dialog_title,
            description = R.string.timetable_dialog_description,
            confirmButtonText = R.string.timetable_dialog_ok,
            cancelButtonText = R.string.timetable_dialog_cancel,
            action = lessonId
        )
        dialog.setTargetFragment(this, 0)
        dialog.show(parentFragmentManager, "notification")
    }

    override fun onDialogPositiveClick(action: Long) {
        viewModel.onDeleteDialogOkButtonClick(action)
    }
}
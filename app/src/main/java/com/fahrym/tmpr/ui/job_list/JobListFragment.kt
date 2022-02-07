package com.fahrym.tmpr.ui.job_list

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.fahrym.tmpr.R
import com.fahrym.tmpr.databinding.FragmentJobListBinding
import java.util.*

class JobListFragment : Fragment(), JobListViewModel.JobListListener {

    private val viewModel: JobListViewModel by activityViewModels()
    private lateinit var binding: FragmentJobListBinding
    private var cal: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_job_list, container, false)
        binding.apply {
            viewModel = this@JobListFragment.viewModel
            lifecycleOwner = this@JobListFragment
            lister = this@JobListFragment
            adapter = JobListAdapter(listOf())
        }
        return binding.root
    }

    private val dateSetListener =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)


            viewModel.submitJobList(cal.time)
        }

    override fun onCalenderClicked() {
        val datePick = DatePickerDialog(
            requireContext(),
            dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )

        datePick.datePicker.minDate = System.currentTimeMillis() - 1000
        datePick.show()
    }

    override fun onNextScreen() {
        val action = JobListFragmentDirections.actionJobListFragmentToBlankFragment()
        findNavController().navigate(action)
    }
}
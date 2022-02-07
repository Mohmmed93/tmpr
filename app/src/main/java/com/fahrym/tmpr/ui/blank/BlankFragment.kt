package com.fahrym.tmpr.ui.blank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.fahrym.tmpr.R
import com.fahrym.tmpr.databinding.FragmentBlankBinding

class BlankFragment : Fragment(), BlankViewModel.BlackListener {

    private val viewModel: BlankViewModel by activityViewModels()
    private lateinit var binding: FragmentBlankBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false)
        binding.apply {
            viewModel = this@BlankFragment.viewModel
            lifecycleOwner = this@BlankFragment
            lister = this@BlankFragment
        }
        return binding.root
    }

    override fun onButtonClick() {
        val action = BlankFragmentDirections.actionBlankFragmentToJobListFragment()
        findNavController().navigate(action)
    }
}
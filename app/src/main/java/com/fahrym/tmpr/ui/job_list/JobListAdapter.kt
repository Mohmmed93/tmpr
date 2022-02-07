package com.fahrym.tmpr.ui.job_list

import com.fahrym.tmpr.R
import com.fahrym.tmpr.data.remote.model.JobListResponse
import com.fahrym.tmpr.databinding.JobItemBinding
import com.fahrym.tmpr.ui.base.BaseAdapter

class JobListAdapter(private val items: List<JobListResponse.Data>) :
    BaseAdapter<JobItemBinding, JobListResponse.Data>(items) {

    override val layoutId: Int = R.layout.job_item

    override fun bind(binding: JobItemBinding, item: JobListResponse.Data) {
        binding.apply {
            jobList = item
            executePendingBindings()
        }
    }
}
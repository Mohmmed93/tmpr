package com.fahrym.tmpr.ui.job_list

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahrym.tmpr.data.TmprRepositoryImpl
import com.fahrym.tmpr.data.remote.model.JobListResponse
import com.fahrym.tmpr.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class JobListViewModel @Inject constructor(
    private val repository: TmprRepositoryImpl,
) : ViewModel() {
    private val _response = MutableLiveData<List<JobListResponse.Data>>()
    val response: LiveData<List<JobListResponse.Data>> = _response

    private val _showProgressBar = MutableLiveData(false)
    val showProgressBar: LiveData<Boolean> = _showProgressBar

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    init {
        val currentDate = Calendar.getInstance().time
        submitJobList(currentDate)
    }

    private fun fetchJobList(filter: String) = viewModelScope.launch {
        repository.getJobList(filter).collect { values ->
            when (values) {
                is NetworkResult.Success<*> -> {
                    _showProgressBar.postValue(false)
                    values.data?.let {
                        _response.value = values.data.data
                    }
                }

                is NetworkResult.Error<*> -> {
                    _showProgressBar.postValue(false)
                }

                is NetworkResult.Loading<*> -> {
                    _showProgressBar.postValue(true)
                }
            }
        }
    }

    public fun submitJobList(date: Date) {
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        val chooseDate = sdf.format(date.time)

        val forDisplay = "EEEE dd MMMM"
        val sdfDisplay = SimpleDateFormat(forDisplay, Locale.getDefault())
        val display = sdfDisplay.format(date.time)

        _date.value = display
        _showProgressBar.postValue(true)
        viewModelScope.launch {
            fetchJobList(chooseDate)
        }
    }

    interface JobListListener {
        fun onCalenderClicked()
        fun onNextScreen()
    }
}


package com.fahrym.tmpr.ui.blank

import androidx.lifecycle.ViewModel
import com.fahrym.tmpr.data.TmprRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BlankViewModel @Inject constructor(
    private val repository: TmprRepositoryImpl,
) : ViewModel() {

    interface BlackListener {
        fun onButtonClick()
    }
}


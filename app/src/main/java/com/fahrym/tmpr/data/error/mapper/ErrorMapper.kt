package com.fahrym.tmpr.data.error.mapper

import android.content.Context
import com.fahrym.tmpr.R
import com.fahrym.tmpr.data.error.*
import com.task.data.error.*
import com.task.data.error.mapper.ErrorMapperSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/*class ErrorMapper @Inject constructor(@ApplicationContext val context: Context) :
    ErrorMapperSource {

    override fun getErrorString(errorId: Int): String {
        return context.getString(errorId)
    }

    override val errorsMap: Map<Int, String>
        get() = mapOf(
            Pair(NO_INTERNET_CONNECTION, getErrorString(R.string.no_internet)),
        ).withDefault { getErrorString(R.string.network_error) }
}*/

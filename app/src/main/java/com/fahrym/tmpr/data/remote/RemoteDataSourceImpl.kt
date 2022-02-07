package com.fahrym.tmpr.data.remote

import com.fahrym.tmpr.data.network.TmprApi
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val service: TmprApi,
) {

    suspend fun getJobList(
        filter: String,
    ) = service.getJobList(filter)
}
package com.fahrym.tmpr.data.network

import com.fahrym.tmpr.data.remote.model.JobListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TmprApi {

    @GET(PATH)
    suspend fun getJobList(
       @Query("filter[date]") filter: String,
//        @Query("page") page: Int = STARTING_PAGE_INDEX,
//        @Query("size") size: Int = PAGING_SIZE,
    ): Response<JobListResponse>

    companion object {
        const val PATH = "api/v3/shifts"
        const val STARTING_PAGE_INDEX = 1
        const val PAGING_SIZE = 50
    }
}
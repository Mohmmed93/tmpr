package com.fahrym.tmpr.data

import com.fahrym.tmpr.data.remote.RemoteDataSourceImpl
import com.fahrym.tmpr.data.remote.base.BaseApiResponse
import com.fahrym.tmpr.data.remote.model.JobListResponse
import com.fahrym.tmpr.util.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class TmprRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSourceImpl
) : BaseApiResponse() {

    suspend fun getJobList(filter: String): Flow<NetworkResult<JobListResponse>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getJobList(filter) })
        }.flowOn(Dispatchers.IO)
    }
}
package com.fahrym.tmpr.job_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.fahrym.tmpr.data.TmprRepositoryImpl
import com.fahrym.tmpr.data.remote.model.JobListResponse
import com.fahrym.tmpr.ui.job_list.JobListViewModel
import com.fahrym.tmpr.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class JobListViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiHelper: TmprRepositoryImpl

    @Mock
    private lateinit var apiUsersObserver: Observer<List<JobListResponse.Data>>

    @Before
    fun setUp() {
        // do something if required
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyFlow<JobListResponse>())
                .`when`(apiHelper)
                .getJobList("2022-02-07")
            val viewModel = JobListViewModel(apiHelper)
            viewModel.response.observeForever(apiUsersObserver)
            verify(apiHelper).getJobList("2022-02-07")
            // verify(apiUsersObserver).onChanged(emptyList())
            viewModel.response.removeObserver(apiUsersObserver)
        }
    }

    @After
    fun tearDown() {
        // do something if required
    }

}
package uk.co.sentinelweb.qcstechtest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import uk.co.sentinelweb.qcstechtest.net.RepoRepository
import uk.co.sentinelweb.qcstechtest.providers.CoroutineContextProvider

class CommitViewModel (
    private val repository: RepoRepository,
    private val modelMapper: CommitModelMapper,
    private val contextProvider: CoroutineContextProvider
) : ViewModel() {

    private lateinit var loadJob: Job
    private val returnLive =  MutableLiveData<List<CommitModel>>()

    fun commits(): LiveData<List<CommitModel>> {
        loadData()
        return returnLive
    }

    fun loadData() {
//        loadJob = CoroutineScope(contextProvider.IO).launch {
//            val commitsResult = repository.getCommits()
//            withContext(contextProvider.Main) {
//                returnLive.value = commitsResult
//                        .map { modelMapper.map(it) }
//            }
//        }
        CoroutineScope(contextProvider.Main).launch {
            // move to another Thread
            val commitsResult = withContext(contextProvider.IO) {
                repository.getCommits().map { modelMapper.map(it) }
            }
            returnLive.value = commitsResult
        }
    }

    fun releaseJob() {
        if (::loadJob.isInitialized && loadJob.isActive) {
            loadJob.cancel()
        }
    }
}

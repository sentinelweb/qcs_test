package uk.co.sentinelweb.qcstechtest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import uk.co.sentinelweb.qcstechtest.net.RepoRepository

class CommitViewModel (
    private val repository: RepoRepository,
    private val modelMapper: CommitModelMapper
) : ViewModel() {

    private lateinit var loadJob: Job
    private val returnLive =  MutableLiveData<List<CommitModel>>()

    fun commits(): LiveData<List<CommitModel>> {
        loadData()
        return returnLive
    }

    fun loadData() {
        loadJob = CoroutineScope(Dispatchers.IO).launch {
            val commitsResult = repository.getCommits()
            withContext(Dispatchers.Main) {
                returnLive.value = commitsResult
                        .map { modelMapper.map(it) }
            }
        }
    }

    fun releaseJob() {
        if (::loadJob.isInitialized && loadJob.isActive) {
            loadJob.cancel()
        }
    }
}

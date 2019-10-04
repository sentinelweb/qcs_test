package uk.co.sentinelweb.qcstechtest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import uk.co.sentinelweb.qcstechtest.net.RepoRepository
import uk.co.sentinelweb.qcstechtest.providers.CoroutineContextProvider

class CommitViewModel(
    private val repository: RepoRepository,
    private val modelMapper: CommitModelMapper,
    private val contextProvider: CoroutineContextProvider
) : ViewModel() {

    internal var jobs = mutableListOf<Job>()
    private val returnLive = MutableLiveData<List<CommitModel>>()

    fun commits(): LiveData<List<CommitModel>> {
        if (returnLive.value?.isEmpty() ?: true) {
            loadData()
        }
        return returnLive
    }

    fun loadData() {
        jobs.add(CoroutineScope(contextProvider.Main).launch {
            // move to another Thread
            val commitsResult = withContext(contextProvider.IO) {
                repository.getCommits().map { modelMapper.map(it) }
            }
            returnLive.value = commitsResult
        })
    }

    fun releaseJobs() {
        jobs.forEach{job ->
            if (job.isActive) job.cancel()
        }
        jobs.clear()
    }
}

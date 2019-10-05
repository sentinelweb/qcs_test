package uk.co.sentinelweb.qcstechtest.net

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uk.co.sentinelweb.qcstechtest.domain.Commit
import uk.co.sentinelweb.qcstechtest.net.service.commit.CommitInteractor
import uk.co.sentinelweb.qcstechtest.providers.CoroutineContextProvider

class RepoRetrofitRepository constructor(
    private val commitInteractor: CommitInteractor,
    private val contextProvider: CoroutineContextProvider
) : RepoRepository {

    private val commitsLiveData = MutableLiveData<List<Commit>>()
    @VisibleForTesting
    internal var jobs = mutableListOf<Job>()

    override fun getCommits(): LiveData<List<Commit>> {
        reloadCommits()
        return commitsLiveData
    }

    override fun reloadCommits() {
        jobs.add(CoroutineScope(contextProvider.Main).launch {
            // move to another Thread
            val commitsResult = withContext(contextProvider.IO) {
                commitInteractor.getCommits()
            }
            commitsLiveData.value = commitsResult
        })
    }

    override fun releaseResources() {
        jobs.forEach { job ->
            if (job.isActive) job.cancel()
        }
        jobs.clear()
    }
}
package uk.co.sentinelweb.qcstechtest.net

import androidx.lifecycle.LiveData
import uk.co.sentinelweb.qcstechtest.domain.Commit

interface RepoRepository {
    fun getCommits(): LiveData<List<Commit>>

    fun reloadCommits()

    fun releaseResources()
}
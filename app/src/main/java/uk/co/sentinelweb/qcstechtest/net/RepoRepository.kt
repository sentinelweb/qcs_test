package uk.co.sentinelweb.qcstechtest.net

import uk.co.sentinelweb.qcstechtest.domain.Commit

interface RepoRepository {
    suspend fun getCommits(): List<Commit>
}
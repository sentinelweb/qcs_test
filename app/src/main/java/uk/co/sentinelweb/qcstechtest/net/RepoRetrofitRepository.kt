package uk.co.sentinelweb.qcstechtest.net

import uk.co.sentinelweb.qcstechtest.domain.Commit
import uk.co.sentinelweb.qcstechtest.net.service.commit.CommitInteractor

class RepoRetrofitRepository constructor(
    private val commitInteractor: CommitInteractor
): RepoRepository {

    override suspend fun getCommits(): List<Commit> {
        return commitInteractor.getCommits()
    }

}
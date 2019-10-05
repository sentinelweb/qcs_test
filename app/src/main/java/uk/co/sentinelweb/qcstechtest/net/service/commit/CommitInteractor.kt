package uk.co.sentinelweb.qcstechtest.net.service.commit

import uk.co.sentinelweb.qcstechtest.domain.Commit
import uk.co.sentinelweb.qcstechtest.net.service.RepoService


class CommitInteractor(
    private val service: RepoService,
    private val mapper: CommitDtoMapper
) {

    suspend fun getCommits(): List<Commit> {
        return service.listCommits()
            .map { mapper.map(it) }
    }

}
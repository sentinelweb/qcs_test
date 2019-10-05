package uk.co.sentinelweb.qcstechtest.ui.main

import uk.co.sentinelweb.qcstechtest.domain.Commit

class CommitModelMapper {

    fun map(commitDomain: Commit): CommitModel {

        return CommitModel(
            commitDomain.sha,
            commitDomain.message,
            commitDomain.date.toString(),
            commitDomain.author.name,
            commitDomain.author.imageUrl
        )
    }
}
package uk.co.sentinelweb.qcstechtest.net.service.commit

import uk.co.sentinelweb.qcstechtest.domain.Commit
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class CommitDtoMapper() {

    fun map(commitRecordDto:CommitRecordDto):Commit {
        val parse = ZonedDateTime.parse(commitRecordDto.commit.committer.date, DateTimeFormatter.ISO_DATE_TIME)
            .withZoneSameInstant(ZoneId.systemDefault())
        return Commit(
            commitRecordDto.sha,
            commitRecordDto.commit.message,
            parse,
            Commit.Author(
                commitRecordDto.commit.committer.name,
                commitRecordDto.commit.committer.email,
                commitRecordDto.committer.avatar_url
            )
        )
    }
}
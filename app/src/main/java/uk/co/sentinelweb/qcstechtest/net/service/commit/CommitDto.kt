package uk.co.sentinelweb.qcstechtest.net.service.commit

data class CommitRecordDto(
    val sha: String,
    val commit: CommitDto,
    val committer: CommitterOverviewDto?
) {
    data class CommitDto(
        val message: String,
        val committer: CommitterDto
    )

    data class CommitterDto(
        val name: String,
        val email: String,
        val date: String
    )

    data class CommitterOverviewDto(
        val avatar_url: String
    )
}
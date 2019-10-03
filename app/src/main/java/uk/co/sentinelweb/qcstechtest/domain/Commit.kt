package uk.co.sentinelweb.qcstechtest.domain

import java.time.ZonedDateTime

data class Commit(
    val sha: String,
    val message: String,
    val date: ZonedDateTime,
    val author: Author
) {
    data class Author(
        val name: String,
        val email: String,
        val imageUrl: String
    )
}
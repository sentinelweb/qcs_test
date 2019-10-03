package uk.co.sentinelweb.qcstechtest.net.service

import retrofit2.http.GET
import uk.co.sentinelweb.qcstechtest.net.service.commit.CommitRecordDto


interface RepoService {

    @GET("JetBrains/kotlin/commits")
    suspend fun listCommits(): List<CommitRecordDto>
}
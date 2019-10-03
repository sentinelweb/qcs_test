package uk.co.sentinelweb.qcstechtest.net.service.commit

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import uk.co.sentinelweb.wtestapp.net.client.RetrofitFactory


class CommitInteractorTest {

    private lateinit var sut: CommitInteractor

    @Before
    fun setUp() {
        val retrofitFactory = RetrofitFactory()
        sut = CommitInteractor(retrofitFactory.createRepoService(retrofitFactory.createClient()), CommitDtoMapper())
    }

    @Test
    fun getCommits() {
        runBlocking {
            val listCommits = sut.getCommits()

            Assert.assertNotNull(listCommits)
            Assert.assertTrue("should be size = 20", listCommits.size == 30)
        }
    }
}
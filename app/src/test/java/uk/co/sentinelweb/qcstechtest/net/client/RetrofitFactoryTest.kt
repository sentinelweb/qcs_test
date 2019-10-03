package uk.co.sentinelweb.qcstechtest.net.client

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import uk.co.sentinelweb.wtestapp.net.client.RetrofitFactory

class RetrofitFactoryTest {

    private lateinit var sut: RetrofitFactory

    @Before
    fun setUp() {
        sut = RetrofitFactory()
    }

    @Test
    fun createClient() {
        val createClient = sut.createClient()
        assertNotNull(createClient)
        assertEquals(createClient.baseUrl().toString(), RetrofitFactory.BASE_URL)
    }

    @Test
    fun createCakeListService() {
        val createCakeListService = sut.createRepoService(sut.createClient())
        runBlocking {
            val listCommits = createCakeListService.listCommits()

            assertNotNull(listCommits)
            assertTrue("should be size = 20", listCommits.size == 30)
        }
    }
}
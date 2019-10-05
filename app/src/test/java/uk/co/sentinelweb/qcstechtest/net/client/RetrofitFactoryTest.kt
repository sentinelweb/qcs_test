package uk.co.sentinelweb.qcstechtest.net.client

import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import uk.co.sentinelweb.qcstechtest.net.service.RepoService
import uk.co.sentinelweb.wtestapp.net.client.RetrofitFactory

class RetrofitFactoryTest {
    @Mock
    lateinit var mockRetrofit: Retrofit

    private lateinit var sut: RetrofitFactory


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = RetrofitFactory()
    }

    @Test
    fun createClient() {
        val createClient = sut.createClient()
        assertNotNull(createClient)
        assertEquals(createClient.baseUrl().toString(), RetrofitFactory.BASE_URL)
    }

    @Test
    fun mockCakeListService() {
        val mockRepoService = mock(RepoService::class.java)
        whenever(mockRetrofit.create(RepoService::class.java)).thenReturn(mockRepoService)
        val createRepoService = sut.createRepoService(mockRetrofit)
        assertSame(createRepoService, mockRepoService)
    }

    @Test
    @Ignore("Runs actual API - manual testing only")
    fun createCakeListService() {
        val createCakeListService = sut.createRepoService(sut.createClient())
        runBlocking {
            val listCommits = createCakeListService.listCommits()

            assertNotNull(listCommits)
            assertTrue("should be size = 20", listCommits.size == 30)
        }
    }

}
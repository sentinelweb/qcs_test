package uk.co.sentinelweb.qcstechtest.net

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.annotations.Fixture
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import uk.co.sentinelweb.qcstechtest.domain.Commit
import uk.co.sentinelweb.qcstechtest.net.service.commit.CommitInteractor
import uk.co.sentinelweb.qcstechtest.providers.SingleThreadRule
import uk.co.sentinelweb.qcstechtest.providers.TestCoroutineContextProvider

class RepoRetrofitRepositoryTest {
    @Mock
    lateinit var mockCommitInteractor: CommitInteractor
    @Fixture
    lateinit var fixtDomain: List<Commit>

    @Rule
    @JvmField
    val singleThreadRule = SingleThreadRule()
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var sut: RepoRetrofitRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        FixtureAnnotations.initFixtures(this)

        sut = RepoRetrofitRepository(mockCommitInteractor, TestCoroutineContextProvider())
    }

    @Test
    fun getCommits() {
        val actual = sut.getCommits()

        assertNotNull(actual)
    }

    @Test
    fun reloadCommits() {
        runBlocking {
            whenever(mockCommitInteractor.getCommits()).thenReturn(fixtDomain)

            sut.reloadCommits()

            verify(mockCommitInteractor).getCommits()
        }
    }

    @Test
    fun testLiveData() {
        runBlocking {
            whenever(mockCommitInteractor.getCommits()).thenReturn(fixtDomain)

            val liveData = sut.getCommits()

            verify(mockCommitInteractor).getCommits()

            assertEquals(liveData.value!!.size, fixtDomain.size)
        }
    }

    @Test
    fun releaseResources() {
        sut.getCommits()
        assertEquals(sut.jobs.size, 1)

        sut.releaseResources()
        assertEquals(sut.jobs.size, 0)

    }
}
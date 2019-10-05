package uk.co.sentinelweb.qcstechtest.net.service.commit

import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.annotations.Fixture
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.assertSame
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import uk.co.sentinelweb.qcstechtest.domain.Commit
import uk.co.sentinelweb.qcstechtest.net.service.RepoService
import uk.co.sentinelweb.qcstechtest.providers.SingleThreadRule
import uk.co.sentinelweb.wtestapp.net.client.RetrofitFactory


class CommitInteractorTest {
    @Mock
    lateinit var mockRepoService: RepoService
    @Mock
    lateinit var mockCommitDtoMapper: CommitDtoMapper
    @Fixture
    lateinit var commitDtoList: List<CommitRecordDto>
    @Fixture
    lateinit var commitDomainList: List<Commit>

    @Rule
    @JvmField
    val singleThreadRule = SingleThreadRule()

    private lateinit var sut: CommitInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        FixtureAnnotations.initFixtures(this)

    }

    @Test
    fun getCommits_mock() {
        sut = CommitInteractor(mockRepoService, mockCommitDtoMapper)
        commitDtoList.forEachIndexed { i, commitRecordDto ->
            whenever(mockCommitDtoMapper.map(commitRecordDto)).thenReturn(
                commitDomainList[i]
            )
        }
        runBlocking {
            whenever(mockRepoService.listCommits()).thenReturn(commitDtoList)

            val listCommits = sut.getCommits()

            Assert.assertNotNull(listCommits)
            listCommits.forEachIndexed { i, commit ->
                assertSame(commit, commitDomainList[i])
            }
        }
    }

    @Test
    @Ignore("Runs actual API - manual testing only")
    fun getCommits() {
        val retrofitFactory = RetrofitFactory()
        sut = CommitInteractor(
            retrofitFactory.createRepoService(retrofitFactory.createClient()),
            CommitDtoMapper()
        )
        runBlocking {
            val listCommits = sut.getCommits()

            Assert.assertNotNull(listCommits)
            Assert.assertTrue("should be size = 20", listCommits.size == 30)
        }
    }
}
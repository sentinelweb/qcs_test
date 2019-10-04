package uk.co.sentinelweb.qcstechtest.ui.main

import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import uk.co.sentinelweb.qcstechtest.net.RepoRepository

/**
 * TODO finish the test
 * - test the call to commits call the repository.
 * - test job is cancelled in release.
 */
class CommitViewModelTest {
    @Mock private lateinit var mockRepoRepository: RepoRepository
    @Mock private lateinit var mockCommitModelMapper: CommitModelMapper

    private lateinit var sut:CommitViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        sut = CommitViewModel(mockRepoRepository, mockCommitModelMapper)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun commits() {
        runBlocking {
            val actual = async { sut.commits() }
            assertNotNull(actual)
            verify(mockRepoRepository).getCommits()
        }
    }

    @Test
    fun loadData() {
    }

    @Test
    fun releaseJob() {

    }
}
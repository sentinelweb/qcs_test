package uk.co.sentinelweb.qcstechtest.ui.main

import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import uk.co.sentinelweb.qcstechtest.net.RepoRepository

class CommitViewModelTest {
    @Mock
    private lateinit var mockRepoRepository: RepoRepository
    @Mock
    private lateinit var mockCommitModelMapper: CommitModelMapper

    private lateinit var sut: CommitViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = CommitViewModel(mockRepoRepository, mockCommitModelMapper)
    }

    @Test
    fun commits() {
        val liveData = sut.commits()
        assertNotNull(liveData)
    }

    @Test
    fun reloadData() {
        sut.reloadData()
        verify(mockRepoRepository).reloadCommits()
    }

    @Test
    fun release() {
        sut.release()
        verify(mockRepoRepository).releaseResources()
    }
}
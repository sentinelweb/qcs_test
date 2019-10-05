package uk.co.sentinelweb.qcstechtest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import uk.co.sentinelweb.qcstechtest.domain.Commit
import uk.co.sentinelweb.qcstechtest.net.RepoRepository

class CommitViewModel(
    private val repository: RepoRepository,
    private val modelMapper: CommitModelMapper
) : ViewModel() {

    fun commits(): LiveData<List<CommitModel>> {
        return Transformations.map(repository.getCommits(), { list: List<Commit> ->
            list.map { modelMapper.map(it) }
        })
    }

    fun reloadData() {
        repository.reloadCommits()
    }

    fun release() {
        repository.releaseResources()
    }
}

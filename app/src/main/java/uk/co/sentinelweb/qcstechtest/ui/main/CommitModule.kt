package uk.co.sentinelweb.qcstechtest.ui.main

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class CommitModule {
    companion object {
        val commitModule = module {
            viewModel { CommitViewModel(get(), CommitModelMapper()) }
        }
    }
}
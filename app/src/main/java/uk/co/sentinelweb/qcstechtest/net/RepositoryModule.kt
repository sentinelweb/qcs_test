package uk.co.sentinelweb.qcstechtest.net

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uk.co.sentinelweb.qcstechtest.net.service.RepoService
import uk.co.sentinelweb.qcstechtest.net.service.commit.CommitDtoMapper
import uk.co.sentinelweb.qcstechtest.net.service.commit.CommitInteractor
import uk.co.sentinelweb.qcstechtest.providers.CoroutineContextProvider
import uk.co.sentinelweb.qcstechtest.ui.main.CommitModelMapper
import uk.co.sentinelweb.qcstechtest.ui.main.CommitViewModel
import uk.co.sentinelweb.wtestapp.net.client.RetrofitFactory

class RepositoryModule {

    companion object {
        val repositoryModule = module {
            single<RetrofitFactory> { RetrofitFactory() }
            single<RepoService> {
                val retrofitFactory = get() as RetrofitFactory
                retrofitFactory.createRepoService(retrofitFactory.createClient())
            }

            single<RepoRepository> {
                RepoRetrofitRepository(CommitInteractor(get(), CommitDtoMapper()))
            }

            viewModel { CommitViewModel(get(), CommitModelMapper(), CoroutineContextProvider()) }
        }

    }
}
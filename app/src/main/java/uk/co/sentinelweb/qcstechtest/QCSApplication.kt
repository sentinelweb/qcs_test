package uk.co.sentinelweb.qcstechtest

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import uk.co.sentinelweb.qcstechtest.net.RepositoryModule
import uk.co.sentinelweb.qcstechtest.ui.main.CommitModule

class QCSApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@QCSApplication)
            modules(
                listOf(
                    RepositoryModule.repositoryModule,
                    CommitModule.commitModule
                )
            )
        }
    }
}
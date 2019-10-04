package uk.co.sentinelweb.qcstechtest.providers

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestCoroutineContextProvider : CoroutineContextProvider() {
    override val Main: CoroutineContext = Dispatchers.Main
    override val IO: CoroutineContext = Dispatchers.Main
}
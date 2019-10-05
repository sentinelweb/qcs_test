package uk.co.sentinelweb.qcstechtest.providers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class SingleThreadRule() : TestRule {
    private val mainThreadSurrogate = TestCoroutineDispatcher()

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {

                Dispatchers.setMain(mainThreadSurrogate)
                try {
                    base!!.evaluate()
                } catch (ex: Throwable) {
                    ex.printStackTrace(System.err)
                }

                Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
                mainThreadSurrogate.cleanupTestCoroutines()
            }
        }
    }
}
package com.fgdc.core.testcore

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule

@ExperimentalCoroutinesApi
open class BaseTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    fun runBlocking(block: suspend TestCoroutineScope.() -> Unit) =
        this.coroutinesTestRule.testDispatcher.runBlockingTest { block() }
}

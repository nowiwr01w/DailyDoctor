package data.dispatchers

import domain.dispatchers.AppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

data class TestDispatchersImpl(
    override val default: CoroutineDispatcher = Dispatchers.Unconfined,
    override val main: CoroutineDispatcher = Dispatchers.Unconfined,
    override val io: CoroutineDispatcher = Dispatchers.Unconfined
) : AppDispatchers
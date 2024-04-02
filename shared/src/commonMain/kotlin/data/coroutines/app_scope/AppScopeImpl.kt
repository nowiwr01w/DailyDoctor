package data.coroutines.app_scope

import domain.coroutines.app_scope.AppScope
import domain.coroutines.dispatchers.AppDispatchers
import kotlinx.coroutines.CoroutineScope

class AppScopeImpl(dispatchers: AppDispatchers): AppScope {
    override val scope = CoroutineScope(dispatchers.io)
}
package com.nowiwr01p.model.coroutines.app_scope

import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import kotlinx.coroutines.CoroutineScope

class AppScopeImpl(dispatchers: AppDispatchers): AppScope {
    override val scope = CoroutineScope(dispatchers.io)
}
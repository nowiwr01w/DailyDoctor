package data.di

import data.coroutines.app_scope.AppScopeImpl
import data.coroutines.dispatchers.AppDispatchersImpl
import domain.coroutines.app_scope.AppScope
import domain.coroutines.dispatchers.AppDispatchers
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val moduleCoroutines = DI.Module("CoroutinesModule") {
    /**
     * DISPATCHERS
     */
    bindSingleton<AppDispatchers> {
        AppDispatchersImpl()
    }
    /**
     * APP SCOPE
     */
    bindSingleton<AppScope> {
        AppScopeImpl(dispatchers = instance<AppDispatchers>())
    }
}
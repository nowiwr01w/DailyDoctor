package com.nowiwr01p.model.di

import com.nowiwr01p.model.coroutines.app_scope.AppScope
import com.nowiwr01p.model.coroutines.app_scope.AppScopeImpl
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchersImpl
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val moduleBaseCoroutines = DI.Module("BaseCoroutinesModule") {
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
        AppScopeImpl(
            dispatchers = instance<AppDispatchers>()
        )
    }
}
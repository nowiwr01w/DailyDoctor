package com.nowiwr01p.model.di

import com.nowiwr01p.model.coroutines.app_scope.AppScope
import com.nowiwr01p.model.coroutines.app_scope.AppScopeImpl
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchersImpl
import org.koin.dsl.module

val moduleBaseCoroutines = module {
    /**
     * DISPATCHERS
     */
    single<AppDispatchers> {
        AppDispatchersImpl()
    }
    /**
     * APP SCOPE
     */
    single<AppScope> {
        AppScopeImpl(
            dispatchers = get<AppDispatchers>()
        )
    }
}